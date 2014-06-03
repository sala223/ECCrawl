package com.eccrawl.core;

import java.io.Serializable;
import java.util.Date;

public class ProductLink implements Serializable {

	private static final long serialVersionUID = 1L;

	private String url;

	private Date lastVisit;

	public ProductLink(String url) {
		this.url = url;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		ProductLink other = (ProductLink) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductLink [url=" + url + ", lastVisit=" + lastVisit + "]";
	}

}
