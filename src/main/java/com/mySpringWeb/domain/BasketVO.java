package com.mySpringWeb.domain;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BasketVO {
	private String user_id;
	private String book_unique_id;
	
	@Override
    public String toString() {
        return (
            String.format(
                "BasketVO[\n" +
                "\tuser_id=%s\n" +
                "\tbook_unique_id=%s\n" +
                "]",
                user_id,book_unique_id
            )
        );
    }
}
