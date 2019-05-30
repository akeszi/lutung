/**
 * 
 */
package com.microtripit.mandrillapp.lutung.model;

import com.microtripit.mandrillapp.lutung.http.MandrillHttpResponse;
import com.microtripit.mandrillapp.lutung.http.MandrillUrlFetcher;
import com.microtripit.mandrillapp.lutung.logging.Logger;
import com.microtripit.mandrillapp.lutung.logging.LoggerFactory;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError.MandrillError;


import java.io.IOException;

/**
 * @author rschreijer
 * @since Feb 21, 2013
 */
public final class MandrillRequestDispatcher {
    private static final Logger log = LoggerFactory.getLogger(MandrillRequestDispatcher.class);

	public static final <T> T execute(final MandrillRequest<T> requestModel, MandrillUrlFetcher urlFetcher) throws MandrillApiError, IOException {

            log.debug("starting request '" +requestModel.getUrl()+ "'");
            MandrillHttpResponse response = urlFetcher.fetch(requestModel);
			if( requestModel.validateResponseStatus(response.getStatusCode()) ) {
				try {
					return requestModel.handleResponse(response.getResponseString());
					
				} catch(final HandleResponseException e) {
					throw new IOException(
							"Failed to parse response from request '" 
							+requestModel.getUrl()+ "'", e);
				}
				
			} else {
				// ==> compile mandrill error!
				MandrillError error = null;
				try {
				    error = LutungGsonUtils.getGson()
						.fromJson(response.getResponseString(), MandrillError.class);
				} catch (Throwable ex) {
				    error = new MandrillError("Invalid Error Format",
				                              "Invalid Error Format",
				                              response.getResponseString(),
				                              response.getStatusCode());
				}

				throw new MandrillApiError(
						"Unexpected http status in response: "
						+response.getStatusCode()).withError(error);
			}
	}

}
