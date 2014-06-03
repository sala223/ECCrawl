package com.eccrawl.core;

import java.util.Date;
import java.util.Iterator;

public interface ProductLinkSource {

	Iterator<ProductLink> iterator();

	void updateLinkLastVisit(ProductLink link, Date lastVisit);

	void addProductLink(String link);

}
