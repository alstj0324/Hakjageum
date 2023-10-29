package com.mySpringWeb.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostVO {
    private int post_id;
    private String board_id;
    private String title;
    private String content;
    private String writer_id;
    private String hobby_id;
    private String book_id;
    private int view_count;
    private String create_at;
    private String update_at;

    @Override
    public String toString() {
        return String.format(
            "PostVO[\n" +
            "\tpost_id=%s\n" +
            "\tboard_id=%s\n" +
            "\ttitle=%s\n" +
            "\tcontent=%s\n" +
            "\twriter_id=%s\n" +
            "\thobby_id=%s\n" +
            "\tbook_id=%s\n" +
            "\tview_count=%s\n" +
            "\tcreate_at=%s\n" +
            "\tupdate_at=%s\n" +
            "]",
            post_id, board_id, title, content, writer_id, hobby_id, book_id, view_count, create_at, update_at
        );
    }
}
