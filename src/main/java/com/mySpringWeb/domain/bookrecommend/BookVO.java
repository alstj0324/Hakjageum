package com.mySpringWeb.domain.bookrecommend;

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
    private String discount;
    private String publisher;

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
                "\tdiscount=%s\n" +
                "\tpublisher=%s\n" +
                "]",
                title, link, image, author, pubdate, description, isbn, discount, publisher
            )
        );
    }
}