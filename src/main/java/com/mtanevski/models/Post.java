package com.mtanevski.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
	
	private String _id;
	private String _rev;
		
	private String title;
	
	private String content;
	
	private User user;
	
	private Date postDate;
	
	private List<String> tags;
	
	public Post(){
		this.content = "";
		this.setUser(new User());
		this.postDate = new Date();
		this.tags  = new ArrayList<String>();
	}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_rev() {
		return _rev;
	}

	public void set_rev(String _rev) {
		this._rev = _rev;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
