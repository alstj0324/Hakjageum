package com.mySpringWeb.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CafeVO {
	private String name;
	private String address;
	private String detail_address;
	private String latitude;
	private String longitude;

	public String toString() {
		return (
			String.format(
				"CafeVO[\n" +
				"\tname=%s\n" +
				"\taddress=%s\n" +
				"\tdetail_address=%s\n" +
				"\tlatitude=%s\n" +
				"\tlongitude=%s\n" +
				"]",
				name, address, detail_address, latitude, longitude
			)
		);
	}
}
