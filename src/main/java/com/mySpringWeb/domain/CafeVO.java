package com.mySpringWeb.domain;

public class CafeVO {
	private String name;
	private String address;
	private String detail_address;
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
		return "CafeVO [name="+name+",address="+address+",detail_address="+detail_address+",latitute="+latitude+",longitude="+longitude+"]";
	}
}
