/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;
import java.util.HashMap;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.http.MandrillUrlFetcher;
import com.microtripit.mandrillapp.lutung.view.MandrillTimeSeries;
import com.microtripit.mandrillapp.lutung.view.MandrillUrl;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public class MandrillUrlsApi extends BaseMandrillApi {


	public MandrillUrlsApi(String key, String rootUrl, MandrillUrlFetcher mandrillUrlFetcher) {
		super(key, rootUrl, mandrillUrlFetcher);
	}

	/**
	 * <p>Get the 100 most clicked URLs.</p>
	 * @return
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 * @since Mar 19, 2013
	 */
	public MandrillUrl[] list() 
			throws MandrillApiError, IOException {
		
		return query("urls/list.json",
				MandrillUtil.paramsWithKey(key), 
				MandrillUrl[].class);
		
	}
	
	/**
	 * <p>Get the 100 most clicked URLs that match 
	 * the search query given.</p>
	 * @param query A search query.
	 * @return An array of {@link MandrillUrl} objects with 
	 * the 100 most clicked URLs matching the search query.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillUrl[] search(final String query) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("q", query);
		return query("urls/search.json",
				params, MandrillUrl[].class);
		
	}
	
	/**
	 * <p>Get the recent history (hourly stats for the 
	 * last 30 days) for a url.</p>
	 * @param url An existing URL.
	 * @return An array of {@link MandrillTimeSeries} objects.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillTimeSeries[] timeSeries(final String url) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("url", url);
		return query("urls/time-series.json",
				params, MandrillTimeSeries[].class);
		
	}
}
