package com.mySpringWeb.domain.webhook;

import lombok.Builder;
import lombok.Data;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class HookVO {
    private String url;
    private String username;
    private String avatarUrl;
    private String content;
    private boolean tts;
    private List<EmbedVO> embedList;

    public static class HookVOBuilder {
        public HookVOBuilder addEmbed(EmbedVO embed) {
            this.embedList.add(embed);
            return this;
        }
    }

    public JSONObject toJSON() {
        if (content == null && (embedList == null || embedList.isEmpty())) throw new IllegalArgumentException("내용 또는 임베드 내용이 없습니다!");
        JSONObject json = new JSONObject();

        json.put("username", username);
        json.put("avatar_url", avatarUrl);
        json.put("content", content);
        json.put("tts", tts);

        if (!(embedList == null || embedList.isEmpty())) {
            List<JSONObject> list = new ArrayList<>();

            for (EmbedVO embedVO : embedList) {
                JSONObject embed = new JSONObject();

                embed.put("title", embedVO.getTitle());
                embed.put("description", embedVO.getDescription());
                embed.put("url", embedVO.getUrl());

                if (embedVO.getColor() != null) {
                    embed.put("color", embedVO.getColor().getRGB());
                }

                if (embedVO.getAuthor() != null) {
                    JSONObject author = new JSONObject();
                    author.put("name", embedVO.getAuthor().getName());
                    author.put("url", embedVO.getAuthor().getUrl());
                    author.put("icon_url", embedVO.getAuthor().getIcon_url());
                    embed.put("author", author);
                }

                if (embedVO.getThumbnail() != null) {
                    JSONObject thumbnail = new JSONObject();
                    thumbnail.put("url", embedVO.getThumbnail().getUrl());
                    embed.put("thumbnail", thumbnail);
                }

                if (embedVO.getImage() != null) {
                    JSONObject image = new JSONObject();
                    image.put("url", embedVO.getImage().getUrl());
                    embed.put("image", image);
                }

                if (embedVO.getFooter() != null) {
                    JSONObject footer = new JSONObject();
                    footer.put("text", embedVO.getFooter().getText());
                    footer.put("icon_url", embedVO.getFooter().getIcon_url());
                    embed.put("footer", footer);
                }

                list.add(embed);
            }

            json.put("embeds", list);
        }

        return json;
    }

    @Override
    public String toString() {
        return toJSON().toString();
    }
}
