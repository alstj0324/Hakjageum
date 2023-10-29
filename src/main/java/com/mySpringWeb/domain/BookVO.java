package com.mySpringWeb.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookVO {
    private String title;
    private String link;
    private String image;
    private String author;
    private String pubdate;
    private String description;
    private String isbn;

    @Override
    public String toString() {
        return (
            String.format(
                "BookVO[\n" +
                "\ttitle=%s\n" +
                "\tlink=%s\n" +
                "\timage=%s\n" +
                "\tauthor=%s\n" +
                "\tpubdate=%s\n" +
                "\tdescription=%s\n" +
                "\tisbn=%s\n" +
                "]",
                title, link, image, author, pubdate, description, isbn
            )
        );
    }
}