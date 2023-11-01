package com.mySpringWeb.domain.board;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentVO {
    private String post_id;
    private String writer_id;
    private String content;
    private String create_at;
    private String delete_at;

    @Override
    public String toString() {
        return String.format(
            "CommentVO[\n" +
            "\tpost_id=%s\n" +
            "\twriter_id=%s\n" +
            "\tcontent=%s\n" +
            "\tcreate_at=%s\n" +
            "\tdelete_at=%s\n" +
            "]",
            post_id, writer_id, content, create_at, delete_at
        );
    }
}
