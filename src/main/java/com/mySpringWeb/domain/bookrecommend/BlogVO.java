package com.mySpringWeb.domain.bookrecommend;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BlogVO {
	private String title;
	private String link;
	private String description;

	public String toString() {
		return String.format(
			"BlogVO[\n" +
			"\ttitle=%s\n" +
			"\tlink=%s\n" +
			"\tdescription=%s\n" +
			"]",
			title, link, description
		);
	}
}
