package com.mySpringWeb.domain.board;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostVO {
    private int id;
    private String board_code;
    private String title;
    private String content;
    private String writer_id;
    private int view_count;
    private String hobby_code;
    private String book_id;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    @Override
    public String toString() {
        return String.format(
            "PostVO[\n" +
            "\tid=%s\n" +
            "\tboard_code=%s\n" +
            "\ttitle=%s\n" +
            "\tcontent=%s\n" +
            "\twriter_id=%s\n" +
            "\tview_count=%s\n" +
            "\thobby_code=%s\n" +
            "\tbook_id=%s\n" +
            "\tcreated_at=%s\n" +
            "\tupdated_at=%s\n" +
            "\tdeleted_at=%s\n" +
            "]",
            id, board_code, title, content, writer_id, view_count, hobby_code, book_id, created_at, updated_at, deleted_at
        );
    }
}
