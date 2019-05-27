/**
 * 
 */
package com.microtripit.mandrillapp.lutung;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;

import com.microtripit.mandrillapp.lutung.controller.TestMandrillHttpUrlFetcher;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;

import com.microtripit.mandrillapp.lutung.MandrillApi;

/**
 * @author rschreijer
 *
 */
public abstract class MandrillTestCase {
	private static final Log log = LogFactory.getLog(MandrillApiTest.class);
	
	protected static MandrillApi mandrillApi;
	protected static String key = null;
	protected static String toAddress = null;
	protected static String fromAddress = null;
	
	/**
	 * <p>If you want to run your own tests, fill in test.properties file.</p>
	 * @return Your Mandrill API key.
	 */
	private static String getMandrillApiKey() {
		if (key == null) {
			getProperties();
		}
		return key;
	}

	protected static final String getToAddress() {
		if (toAddress == null) {
			getProperties();
		}
		return toAddress;
	}

	protected static final String getFromAddress() {
		if (fromAddress == null) {
			getProperties();
		}
		return fromAddress;
	}


	protected static void getProperties() {
		try {
			final InputStream is = new FileInputStream("src/test/resources/test.properties");
			Properties prop = new Properties();
			prop.load(is);

			key = prop.getProperty("api.key");
			toAddress = prop.getProperty("to.address");
			fromAddress = prop.getProperty("from.address");
			is.close();

			if (key == null || key.isEmpty()) {
				throw new IOException("Empty property 'api.key'.");

			}

			if (toAddress == null || toAddress.isEmpty()) {
				throw new IOException("Empty property key 'to.address'");
			}

			if (fromAddress == null || fromAddress.isEmpty()) {
				throw new IOException("Empty property key 'from.address'");
			}

		} catch(final IOException e) {
			log.error("Could not read property files. " +
					"Please fill in properties in resources/test.properties.", e);
		}
	}
	
	@BeforeClass
	public static final void runBeforeClass() {
		final String key = getMandrillApiKey();
		if(key != null) {
			mandrillApi = new MandrillApi(key, new TestMandrillHttpUrlFetcher());
		} else {
			mandrillApi = null;
		}
	}
	
	@Before
	public final void runBefore() {
		Assume.assumeNotNull(mandrillApi);
	}

}
