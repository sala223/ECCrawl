package com.eccrawl.core;

import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public interface ECEntity {

	String getUrl();

	String getEntityGlobalId();
	
	ProxyConfig getProxyConfig();

	ProductLinkSource getProductLinkSource();

	void parse(ECCrawlContext context, HtmlPage page) throws Throwable;

}
