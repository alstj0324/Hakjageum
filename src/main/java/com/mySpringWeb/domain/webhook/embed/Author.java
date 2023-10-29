package com.mySpringWeb.domain.webhook.embed;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {
    private String name;
    private String url;
    private String icon_url;
}
