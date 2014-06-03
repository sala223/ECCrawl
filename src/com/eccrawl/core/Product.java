package com.eccrawl.core;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private String entityGlobalId;

	private String storeId;

	private String productId;

	private String productName;

	private Comments comments = new Comments();

	public Product(String entityGlobalId, String storeId, String productId) {
		this.entityGlobalId = entityGlobalId;
		this.storeId = storeId;
		this.productId = productId;
	}

	public String getEntityGlobalId() {
		return entityGlobalId;
	}

	public String getStoreId() {
		return storeId;
	}

	public String getProductId() {
		return productId;
	}

	public Comments getComments() {
		return comments;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
