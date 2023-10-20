package com.mySpringWeb.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
public class BoardVO {
	private int board_id;
	private int board_type;
	private String title;
	private String content;
	private String writer_id;
	private String book_id;
	private String hobby_category;
	private Date create_at;
	private Date update_at;

	public String toString() {
		return String.format(
            "BoardVO[\n" +
            "\tboard_type=%s\n" +
            "\ttitle=%s\n" +
            "\tcontent=%s\n" +
            "\twriter_id=%s\n" +
            "\tbook_id=%s\n" +
            "\thobby_category=%s\n" +
            "\tcreate_at=%s\n" +
            "\tupdate_at=%s\n" +
            "]",
            board_type, title, content, writer_id, book_id, hobby_category, create_at, update_at
		);
	}
}
