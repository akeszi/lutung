package com.microtripit.mandrillapp.lutung.controller;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.http.MandrillUrlFetcher;


import java.io.IOException;
import java.util.Map;

public abstract class BaseMandrillApi {

	public BaseMandrillApi(String key, String rootUrl, MandrillUrlFetcher mandrillUrlFetcher) {
		this.key = key;
		this.rootUrl = rootUrl;
		this.mandrillUrlFetcher = mandrillUrlFetcher;
	}

	protected <OUT> OUT query (final String endpoint,
	                           final Map<String, Object> params, Class<OUT> responseType) throws IOException, MandrillApiError {
		return MandrillUtil.query(this.rootUrl+ endpoint,
				params, responseType, mandrillUrlFetcher);
	}






	protected final String key;
	protected final String rootUrl;
	protected final MandrillUrlFetcher mandrillUrlFetcher;

}
