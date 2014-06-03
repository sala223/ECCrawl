package com.eccrawl.core.jd;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import com.eccrawl.core.Comment;
import com.eccrawl.core.ECCrawlContext;
import com.eccrawl.core.ECEntity;
import com.eccrawl.core.InMemoryProductLinkSource;
import com.eccrawl.core.Product;
import com.eccrawl.core.ProductLinkSource;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class JDECEntity implements ECEntity {

	private ProductLinkSource linkSource = new InMemoryProductLinkSource();

	private static final Pattern productPagePattern = Pattern.compile("^(http|https)://item.jd.com/([0-9]*?).html");

	public JDECEntity() {
		linkSource.addProductLink("http://item.jd.com/811329.html");
	}

	@Override
	public String getUrl() {
		return "http://www.jd.com";
	}

	@Override
	public String getEntityGlobalId() {
		return "jd";
	}

	@Override
	public void parse(ECCrawlContext context, HtmlPage page) throws Throwable {
		if (isProductPage(page.getUrl().toExternalForm())) {
			abstractProductDetails(page);
		}
	}

	private boolean isProductPage(String url) {
		return productPagePattern.matcher(url).matches();
	}

	private Product abstractProductDetails(HtmlPage page) throws IOException {
		String productName = ((HtmlElement) page.getElementById("product-intro").getByXPath("div/h1").get(0)).getTextContent();
		page = ((HtmlElement) page.getElementById("comments-list").getByXPath("div[1]/ul/li[1]").get(0)).click();
		Product product = new Product(this.getEntityGlobalId(), "", "");
		product.setProductName(productName);
		abstractProductComments(product, page);
		List<?> nextElements = page.getElementById("commentsPage0").getByXPath("a[@class='next']");
		while (nextElements.size() > 0) {
			HtmlElement nextLink = (HtmlElement) nextElements.get(0);
			page = nextLink.click();
			abstractProductComments(product, page);
			nextElements = page.getElementById("commentsPage0").getByXPath("a[@class='next']");
		}
		return product;
	}

	protected void abstractProductComments(Product product, HtmlPage page) {
		List<?> commentElements = page.getElementById("comment-0").getByXPath("./div");
		for (Object commentElement : commentElements) {
			HtmlElement el = (HtmlElement) commentElement;
			List<?> xpathEl = el.getByXPath(".//div[@class='comment-content']/dl/dd[preceding-sibling::dt[1][text()='ÐÄµÃ£º']]");
			List<?> dateEl = el.getByXPath(".//a[@class='date-comment']");
			if (xpathEl.size() > 0 && dateEl.size() > 0) {
				HtmlElement ddElement = (HtmlElement) xpathEl.get(0);
				HtmlElement dateElement = (HtmlElement) dateEl.get(0);
				String comment = ddElement.getTextContent();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date date = null;
				try {
					date = dateFormat.parse(dateElement.getTextContent());
				} catch (ParseException e) {
				}
				product.getComments().addComment(new Comment(date, comment));
			}
		}
	}

	@Override
	public ProxyConfig getProxyConfig() {
		ProxyConfig proxyConfig = new ProxyConfig();
		proxyConfig.setProxyHost("proxy.sha.sap.corp");
		proxyConfig.setProxyPort(8080);
		return proxyConfig;
	}

	@Override
	public ProductLinkSource getProductLinkSource() {
		return linkSource;
	}
}
