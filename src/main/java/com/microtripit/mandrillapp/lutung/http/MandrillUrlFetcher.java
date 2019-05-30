package com.microtripit.mandrillapp.lutung.http;

import com.microtripit.mandrillapp.lutung.model.MandrillRequest;

import java.io.IOException;

public interface MandrillUrlFetcher {
	MandrillHttpResponse fetch(MandrillRequest<?> requestModel) throws IOException;
}
