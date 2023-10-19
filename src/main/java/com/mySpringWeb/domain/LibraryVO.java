package com.mySpringWeb.domain;

public class LibraryVO {
	private String name;
	private String address;
	private String detail_address;
	private String operating_time;
	private String closed;
	private String latitude;
	private String longitude;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetail_address() {
		return detail_address;
	}
	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}
	public String getOperating_time() {
		return operating_time;
	}
	public void setOperating_time(String operating_time) {
		this.operating_time = operating_time;
	}
	public String getClosed() {
		return closed;
	}
	public void setClosed(String closed) {
		this.closed = closed;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String toString() {
		return "LibraryVO [name="+name+",address="+address+",detail_address="+detail_address+",operating_time"+operating_time+",closed="+closed+",latitute="+latitude+",longitude="+longitude+"]";
	}
}
