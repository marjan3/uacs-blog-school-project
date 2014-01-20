package com.mtanevski.models;

public class About {
	
	private String _id;
	
	private String _rev;
		
	private String address;
	
	private String description;
	
	private String email;
	
	private String fax;
	
	private String phone;
	
	public About(){
		this._id="about";
		this.address = "";
		this.description = "";
		this.email = "";
		this.fax = "";
		this.phone = "";
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
