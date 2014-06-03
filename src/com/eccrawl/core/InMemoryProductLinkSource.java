package com.eccrawl.core;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class InMemoryProductLinkSource implements ProductLinkSource {

	private Set<ProductLink> links = new HashSet<ProductLink>();

	@Override
	public Iterator<ProductLink> iterator() {
		return links.iterator();
	}

	@Override
	public void updateLinkLastVisit(ProductLink link, Date lastVisit) {
		link.setLastVisit(lastVisit);
	}

	public void addProductLink(String link) {
		ProductLink pl = new ProductLink(link);
		if (!links.contains(pl)) {
			links.add(pl);
		}
	}

}
