/**
 * 
 */
package com.mtanevski.models;

import java.util.Date;

/**
 * @author Marjan
 *
 */
public class Comment {
	
	private String _id;
	private String _rev;
	
	private String post_id;
	private String comment_id;
	
	private String content;
	
	private User user;
	
	private boolean approved;
	
	private Date postDate;
	
	public Comment(){
		this.post_id = "";
		this.comment_id = "";
		this.content = "";
		this.setUser(new User());
		this.approved = true;
		this.postDate = new Date();
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

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public String getComment_id() {
		return comment_id;
	}

	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	
	
}
