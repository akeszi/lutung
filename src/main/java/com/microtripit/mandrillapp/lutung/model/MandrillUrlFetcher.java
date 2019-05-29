package com.microtripit.mandrillapp.lutung.model;

import java.io.IOException;

public interface MandrillUrlFetcher {
	MandrillHttpResponse fetch(MandrillRequest<?> requestModel) throws IOException;
}
