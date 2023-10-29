package com.mySpringWeb.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlaceVO {
	private String name;
	private String address;
	private String road_address;
	private String phone;
	private String url;
	private String x;
	private String y;

	public String toString() {
		return (
            String.format(
				"CafeVO[\n" +
				"\tname=%s\n" +
				"\taddress=%s\n" +
				"\troad_address=%s\n" +
				"\tphone=%s\n" +
				"\turl=%s\n" +
				"\tx=%s\n" +
				"\ty=%s\n" +
				"]",
				name, address, road_address, phone, url, x, y
			)
		);
	}
}
