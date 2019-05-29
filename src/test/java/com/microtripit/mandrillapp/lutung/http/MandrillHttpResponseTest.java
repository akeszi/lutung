package com.microtripit.mandrillapp.lutung.http;

import junit.framework.Assert;
import org.junit.Test;

public class MandrillHttpResponseTest {

	@Test
	public void testFieldValue() {

		final String testString = "Lorem Ipsum";
		final int testStatusCode = 123;

		MandrillHttpResponse response = new MandrillHttpResponse(testString, testStatusCode);

		Assert.assertEquals(testString, response.getResponseString());
		Assert.assertEquals(testStatusCode, response.getStatusCode());
	}
}
