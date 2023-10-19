package com.mySpringWeb.domain;

import java.sql.Date;

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

    public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getBoard_type() {
		return board_type;
	}

	public void setBoard_type(int board_type) {
		this.board_type = board_type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getHobby_category() {
		return hobby_category;
	}

	public void setHobby_category(String hobby_category) {
		this.hobby_category = hobby_category;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	public Date getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(Date update_at) {
		this.update_at = update_at;
	}

	public String toString() {
        return "BoardVO [board_type=" + board_type + ", title=" + title + ", content=" + content + ", writer_id="
                + writer_id + ", book_id=" + book_id + ", hobby_category=" + hobby_category + ", create_at=" + create_at
                + ", update_at=" + update_at + "]";
    }
}
