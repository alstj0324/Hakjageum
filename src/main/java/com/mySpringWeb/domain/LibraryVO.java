package com.mySpringWeb.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class LibraryVO {
	private String name;
	private String address;
	private String detail_address;
	private String operating_time;
	private String closed;
	private String latitude;
	private String longitude;

	public String toString() {
		return (
			String.format(
				"LibraryVO[\n" +
				"\tname=%s\n" +
				"\taddress=%s\n" +
				"\tdetail_address=%s\n" +
				"\toperating_time=%s\n" +
				"\tclosed=%s\n" +
				"\tlatitude=%s\n" +
				"\tlongitude=%s\n" +
				"]",
				name, address, detail_address, operating_time, closed, latitude, longitude
			)
		);
	}
}
