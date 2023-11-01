package com.mySpringWeb.domain.bookrecommend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketVO {
	private String user_id;
	private String book_unique_id;
	
	public String toString() {
		return String.format(
				"BasketVO[\n" +
				"\tuser_id=%s\n" +
				"\tbook_unique_id=%s\n" +
				"]",
				user_id, book_unique_id
				);
	}
}
