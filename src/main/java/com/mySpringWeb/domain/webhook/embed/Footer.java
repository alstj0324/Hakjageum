package com.mySpringWeb.domain.webhook.embed;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Footer {
    private String text;
    private String icon_url;
}
