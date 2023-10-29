package com.mySpringWeb.domain.webhook;

import lombok.Builder;
import lombok.Data;
import org.json.simple.JSONObject;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

                if (embedVO.getUrl() != null) {
                    embed.put("url", embedVO.getUrl());
                }

                if (embedVO.getTimestamp() != null) {
                    embed.put("timestamp", embedVO.getTimestamp());
                }

                if (embedVO.getColor() != null) {
                    Color color = embedVO.getColor();
                    int rgb = color.getRed();
                    rgb = (rgb << 8) + color.getGreen();
                    rgb = (rgb << 8) + color.getBlue();

                    embed.put("color", rgb);
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
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<String, Object>> entrySet = toJSON().entrySet();
        builder.append("{");

        int i = 0;
        for (Map.Entry<String, Object> entry : entrySet) {
            Object val = entry.getValue();

            if (val == null) continue;
            if (val instanceof String) {
                builder.append(quote(String.valueOf(val)));
            } else if (val instanceof Integer) {
                builder.append(Integer.valueOf(String.valueOf(val)));
            } else if (val instanceof Boolean) {
                builder.append(val);
            } else if (val instanceof JSONObject) {
                builder.append(val);
            } else if (val.getClass().isArray()) {
                builder.append("[");
                int len = Array.getLength(val);
                for (int j = 0; j < len; j++) builder.append(Array.get(val, j).toString()).append(j != len - 1 ? "," : "");
                builder.append("]");
            }
            builder.append(++i == entrySet.size() ? "}" : ",");
        }
        return builder.toString();
    }

    private String quote(String str) {
        return "\"" + str + "\"";
    }
}
