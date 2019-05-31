package com.microtripit.mandrillapp.lutung.http;
import java.io.IOException;

public interface MandrillUrlFetcher {
	MandrillHttpResponse fetchPOST(String payload, String Url) throws IOException;
}
