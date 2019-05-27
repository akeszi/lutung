package com.microtripit.mandrillapp.lutung.model;

public interface MandrillUrlFetcher {

	MandrillHttpResponse fetch(MandrillRequest<?> requestModel);
}
