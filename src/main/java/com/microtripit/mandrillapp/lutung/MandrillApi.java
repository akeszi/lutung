/**
 * 
 */
package com.microtripit.mandrillapp.lutung;

import com.microtripit.mandrillapp.lutung.controller.MandrillExportsApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillInboundApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillIpsApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillMessagesApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillRejectsApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillSendersApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillSubaccountsApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillTagsApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillTemplatesApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillUrlsApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillUsersApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillWebhooksApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillWhitelistsApi;
import com.microtripit.mandrillapp.lutung.http.MandrillUrlFetcher;

/**
 * @author rschreijer
 * @since Mar 17, 2013
 */
public class MandrillApi {
	public static final String rootUrl = "https://mandrillapp.com/api/1.0/";

	private String key;
	private final MandrillUsersApi users;
	private final MandrillUrlFetcher mandrillUrlFetcher;
	private final MandrillMessagesApi messages;
	private final MandrillTagsApi tags;
	private final MandrillRejectsApi rejects;
	private final MandrillWhitelistsApi whitelists;
	private final MandrillSendersApi senders;
	private final MandrillUrlsApi urls;
	private final MandrillTemplatesApi templates;
	private final MandrillWebhooksApi webhooks;
	private final MandrillSubaccountsApi subaccounts;
	private final MandrillInboundApi inbound;
	private final MandrillExportsApi exports;
	private final MandrillIpsApi ips;

	public MandrillApi(final String key, MandrillUrlFetcher mandrillUrlFetcher) {
		this(key, rootUrl, mandrillUrlFetcher);
	}

	public MandrillApi(final String key, final String rootUrl, MandrillUrlFetcher mandrillUrlFetcher) {
		if(key == null) {
			throw new NullPointerException(
					"'key' is null; please provide Mandrill API key");
		}
		if(rootUrl == null) {
			throw new NullPointerException(
					String.format("'rootUrl' is null; please provide Mandrill URL (default: %s)", rootUrl));
		}

		if (mandrillUrlFetcher == null) {
			throw new NullPointerException("'mandrillUrlFetcher' is null");
		}

		this.mandrillUrlFetcher = mandrillUrlFetcher;
		this.key = key;
		users = new MandrillUsersApi(key, rootUrl, mandrillUrlFetcher);
		messages = new MandrillMessagesApi(key, rootUrl, mandrillUrlFetcher);
		tags = new MandrillTagsApi(key, rootUrl, mandrillUrlFetcher);
		rejects = new MandrillRejectsApi(key, rootUrl, mandrillUrlFetcher);
		whitelists = new MandrillWhitelistsApi(key, rootUrl, mandrillUrlFetcher);
		senders = new MandrillSendersApi(key, rootUrl, mandrillUrlFetcher);
		urls = new MandrillUrlsApi(key, rootUrl, mandrillUrlFetcher);
		templates = new MandrillTemplatesApi(key, rootUrl, mandrillUrlFetcher);
		webhooks = new MandrillWebhooksApi(key, rootUrl, mandrillUrlFetcher);
		subaccounts = new MandrillSubaccountsApi(key, rootUrl, mandrillUrlFetcher);
		inbound = new MandrillInboundApi(key, rootUrl, mandrillUrlFetcher);
		exports = new MandrillExportsApi(key, rootUrl, mandrillUrlFetcher);
		ips = new MandrillIpsApi(key, rootUrl, mandrillUrlFetcher);
	}

	/**
	 * @return Your Mandrill API key.
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * <p>Get access to 'users' calls.</p>
	 * @return An object with access to user calls.
	 */
	public MandrillUsersApi users() {
		return users;
	}
	
	public MandrillMessagesApi messages() {
		return messages;
	}
	
	public MandrillTagsApi tags() {
		return tags;
	}
	
	public MandrillRejectsApi rejects() {
		return rejects;
	}
	
	public MandrillWhitelistsApi whitelists() {
		return whitelists;
	}
	
	public MandrillSendersApi senders() {
		return senders;
	}
	
	public MandrillUrlsApi urls() {
		return urls;
	}
	
	public MandrillTemplatesApi templates() {
		return templates;
	}
	
	public MandrillWebhooksApi webhooks() {
		return webhooks;
	}
	
	public MandrillSubaccountsApi subaccounts() {
		return subaccounts;
	}
	
	public MandrillInboundApi inbound() {
		return inbound;
	}
	
	public MandrillExportsApi exports() {
		return exports;
	}
	
	public MandrillIpsApi ips() {
		return ips;
	}

	public MandrillUrlFetcher urlFetcher() {return mandrillUrlFetcher;}
	
}
