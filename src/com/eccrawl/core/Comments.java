package com.eccrawl.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Comments implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Comment> comments = new ArrayList<Comment>();

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
}
