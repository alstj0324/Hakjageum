package com.mySpringWeb.domain.webhook;

import com.mySpringWeb.domain.webhook.embed.*;
import lombok.Builder;
import lombok.Data;

import java.awt.Color;
import java.util.List;

@Data
@Builder
public class EmbedVO {
    private String title;
    private String description;
    private String url;
    private Color color;

    private Footer footer;
    private Thumbnail thumbnail;
    private Author author;
    private Image image;
    private List<Field> fieldList;
    private String timestamp;

    public static class EmbedVOBuilder {
        public EmbedVOBuilder addField(Field field) {
            this.fieldList.add(field);
            return this;
        }
    }
}
