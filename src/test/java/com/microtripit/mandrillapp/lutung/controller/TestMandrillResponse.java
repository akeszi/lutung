package com.microtripit.mandrillapp.lutung.controller;

import com.microtripit.mandrillapp.lutung.model.MandrillHttpResponse;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class TestMandrillResponse implements MandrillHttpResponse {

	public TestMandrillResponse(HttpResponse httpResponse) throws IOException {
		statusCode = httpResponse.getStatusLine().getStatusCode();
		responseString = EntityUtils.toString(httpResponse.getEntity());
	}


	@Override
	public String getResponseString() {
		return responseString;
	}

	@Override
	public int getStatusCode() {
		return statusCode;
	}

	private String responseString;
	private int statusCode;

}
