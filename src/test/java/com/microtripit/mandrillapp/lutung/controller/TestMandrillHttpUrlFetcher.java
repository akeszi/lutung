package com.microtripit.mandrillapp.lutung.controller;

import com.microtripit.mandrillapp.lutung.http.MandrillHttpResponse;
import com.microtripit.mandrillapp.lutung.http.MandrillUrlFetcher;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;

public class TestMandrillHttpUrlFetcher implements MandrillUrlFetcher {
	@Override
	public MandrillHttpResponse fetchPOST(String payload, String url) {

		HttpResponse response = null;
		MandrillHttpResponse result = null;
		try {
			// use proxy?
			final ProxyData proxyData = detectProxyServer(url);
			if (proxyData != null) {
				final HttpHost proxy = new HttpHost(proxyData.host,
						proxyData.port);
				httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
						proxy);
			}
			try {
				response = httpClient.execute(makeRequest(payload, url));
				int statusCode = response.getStatusLine().getStatusCode();
				String responseString = EntityUtils.toString(response.getEntity());

				result = new MandrillHttpResponse(responseString, statusCode);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				EntityUtils.consume(response.getEntity());
			} catch (IOException e) {
			}
		}
		return result;
	}


	/**
	 * See https://hc.apache.org/httpcomponents-core-4.3.x/httpcore/apidocs/org/apache/http/params/HttpConnectionParams.html#setSoTimeout(org.apache.http.params.HttpParams, int)
	 *
	 * A value of 0 means no timeout at all.
	 * The value is expressed in milliseconds.
	 * */
	public static int SOCKET_TIMEOUT_MILLIS = 0;

	/**
	 * See https://hc.apache.org/httpcomponents-core-4.3.x/httpcore/apidocs/org/apache/http/params/HttpConnectionParams.html#setConnectionTimeout(org.apache.http.params.HttpParams, int)
	 *
	 * A value of 0 means no timeout at all.
	 * The value is expressed in milliseconds.
	 * */
	public static int CONNECTION_TIMEOUT_MILLIS = 0;


	private static CloseableHttpClient httpClient;
	private static PoolingHttpClientConnectionManager connexionManager;
	private static RequestConfig defaultRequestConfig;

	static {
		connexionManager = new PoolingHttpClientConnectionManager();
		connexionManager.setDefaultMaxPerRoute(50);
		defaultRequestConfig = RequestConfig.custom()
				.setSocketTimeout(SOCKET_TIMEOUT_MILLIS)
				.setConnectTimeout(CONNECTION_TIMEOUT_MILLIS)
				.setConnectionRequestTimeout(CONNECTION_TIMEOUT_MILLIS).build();
		httpClient = HttpClients.custom().setUserAgent("/Lutung-0.1")
				.setDefaultRequestConfig(defaultRequestConfig)
				.setConnectionManager(connexionManager).useSystemProperties()
				.build();
	}

		private static final ProxyData detectProxyServer(final String url) {
			try {
				final List<Proxy> proxies = ProxySelector.getDefault().select(new URI(url));
				if(proxies != null) {
					for(Proxy proxy : proxies) {
						InetSocketAddress addr = (InetSocketAddress) proxy.address();
						if(addr != null) {
							return new ProxyData(addr.getHostName(), addr.getPort());
						}
					}
				}
				// no proxy detected!
				return null;

			} catch (final Throwable t) {
				return null;

			}
		}


		private static final class ProxyData {
			String host;
			int port;

			protected ProxyData(final String host, final int port) {
				this.host = host;
				this.port = port;
			}

		}

		private HttpRequestBase makeRequest(String payload, String url){
		final StringEntity entity = new StringEntity(payload, "UTF-8");
		entity.setContentType("application/json");
		final HttpPost request = new HttpPost(url);
		request.setEntity(entity);
		return request;
	}
}
