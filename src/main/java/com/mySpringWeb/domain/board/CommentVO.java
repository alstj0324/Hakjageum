package com.mySpringWeb.domain.board;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentVO {
    private int post_id;
    private String writer_id;
    private String content;
    private int comment_id;
    private String nickname;
    private Date created_at;
    private Date deleted_at;

    @Override
    public String toString() {
        return String.format(
            "CommentVO[\n" +
            "\tpost_id=%s\n" +
            "\twriter_id=%s\n" +
            "\tcontent=%s\n" +
            "\tcreated_at=%s\n" +
            "\tdeleted_at=%s\n" +
            "\tcomment_id=%s\n" +
            "\tnickname=%s\n" +
            "]",
            post_id, writer_id, content, created_at, deleted_at, comment_id, nickname
        );
    }
}
