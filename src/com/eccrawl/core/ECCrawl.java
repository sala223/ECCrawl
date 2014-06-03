package com.eccrawl.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;

import com.eccrawl.core.jd.JDECEntity;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ECCrawl {

	private ECEntity entity;
	private WebClient client;

	public ECCrawl(ECEntity entity) {
		this.entity = entity;
		client = new WebClient(BrowserVersion.FIREFOX_24);
		client.waitForBackgroundJavaScript(10000);
		client.getOptions().setJavaScriptEnabled(true);
		client.getOptions().setThrowExceptionOnScriptError(false);
		client.getOptions().setRedirectEnabled(true);
		if (entity.getProxyConfig() != null) {
			client.getOptions().setProxyConfig(entity.getProxyConfig());
		}
		client.getCookieManager().setCookiesEnabled(true);
	}

	public void crawl() {
		ECCrawlContext context = new ECCrawlContextImpl();
		ProductLinkSource productLinkSource = entity.getProductLinkSource();
		Iterator<ProductLink> iterator = productLinkSource.iterator();
		while (iterator.hasNext()) {
			ProductLink link = iterator.next();
			visitLink(context, link);
		}
	}

	protected void visitLink(ECCrawlContext context, ProductLink link) {
		HtmlPage page = this.loadPage(link.getUrl());
		parse(context, page);
	}

	protected void parse(ECCrawlContext context, HtmlPage page) {
		try {
			entity.parse(context, page);
		} catch (Throwable ex) {
		}
	}

	public HtmlPage loadPage(String url) {
		try {
			return client.getPage(url);
		} catch (FailingHttpStatusCodeException ex) {
			throw CrawlException.serverStatusError(ex);
		} catch (MalformedURLException ex) {
			throw CrawlException.malformedURL(ex);
		} catch (IOException ex) {
			throw CrawlException.ioError(ex);
		}
	}

	public static void main(String[] args) {
		JDECEntity entity = new JDECEntity();
		ECCrawl crawl = new ECCrawl(entity);
		crawl.crawl();
	}

}
