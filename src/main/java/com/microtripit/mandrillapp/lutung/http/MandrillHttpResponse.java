package com.microtripit.mandrillapp.lutung.http;

public class MandrillHttpResponse {

	public MandrillHttpResponse(String responseString, int statusCode) {
		this.responseString = responseString;
		this.statusCode = statusCode;
	}

	public String getResponseString() {
		return responseString;
	}

	public int getStatusCode() {
		return statusCode;
	}

	private final String responseString;
	private final int statusCode;
}
