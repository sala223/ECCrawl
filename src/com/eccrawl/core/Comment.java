package com.eccrawl.core;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> attributes = new HashMap<String, Object>();

	private String commentText;

	private Date created;

	private Date modified;

	public Comment(Date created, String commentText) {
		this.commentText = commentText;
		this.created = created;
	}

	public String getCommentText() {
		return commentText;
	}

	public void addAttribute(String attributeName, Object attributeValue) {
		attributes.put(attributeName, attributeName);
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName, Class<T> valueType) {
		return (T) attributes.get(attributeName);
	}

	public Object getAttribute(String attributeName) {
		return attributes.get(attributeName);
	}

	public Date getCreated() {
		return created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

}
