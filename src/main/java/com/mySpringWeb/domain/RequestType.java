package com.mySpringWeb.domain;

import lombok.Getter;

@Getter
public enum RequestType {
    NAVER_BOOKLIST_SEARCH("https://openapi.naver.com/v1/search/book.json"),
    NAVER_BOOK_SEARCH("https://openapi.naver.com/v1/search/book_adv.json"),
    NAVER_BLOG_SEARCH("https://openapi.naver.com/v1/search/blog.json"),
    KAKAO_PLACE_KEYWORD_SEARCH("https://dapi.kakao.com/v2/local/search/keyword.json"),
    NAVER_LOGINAUTH("https://nid.naver.com/oauth2.0/authorize"),
    NAVER_LOGINTOKEN("https://nid.naver.com/oauth2.0/token"),
    NAVER_LOGININFO("https://openapi.naver.com/v1/nid/me"),
    KAKAO_LOGINAUTH("https://kauth.kakao.com/oauth/authorize"),
    KAKAO_LOGINTOKEN("https://kauth.kakao.com/oauth/token"),
    KAKAO_LOGININFO("https://kapi.kakao.com/v2/user/me"),
    IP_LOCATION("http://ip-api.com/json/");

    private final String url;

    RequestType(String url) {
        this.url = url;
    }
}
