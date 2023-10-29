package com.mySpringWeb.domain.webhook.embed;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Field {
    private String name;
    private String value;
    private boolean inline;
}
