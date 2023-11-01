package com.mySpringWeb.utils;

import com.mySpringWeb.domain.bookrecommend.BlogVO;
import com.mySpringWeb.domain.RequestType;
import com.mySpringWeb.domain.webhook.HookLevel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class BlogUtil {
    private final HookUtil hookUtil = new HookUtil();

    public List<BlogVO> getBlogList(String bookname) {
        return getBlogList(bookname, 1, 100);
    }
    public List<BlogVO> getBlogList(String bookname, int start, int display) {
        EnvUtil envUtil = new EnvUtil();
        String clientId = envUtil.getValueByKey("NAVER_CLIENTID");
        String secretId = envUtil.getValueByKey("NAVER_SECRETID");

        RequestUtil requestUtil = new RequestUtil();

        Map<String, Object> headers = new HashMap<>();
        headers.put("X-Naver-Client-Id", clientId);
        headers.put("X-Naver-Client-Secret", secretId);

        Map<String, Object> params = new HashMap<>();
        params.put("query", bookname + " 리뷰");
        params.put("start", start);
        params.put("display", display);
        params.put("sort", "sim");

        JSONObject result = requestUtil.requestData(RequestType.NAVER_BLOG_SEARCH, "GET", headers, params);

        String status = (String) result.get("result_status");

        List<BlogVO> blogs = new ArrayList<>();

        if (Objects.equals(status, "success")) {
            JSONObject data = (JSONObject) result.get("result_data");
            JSONArray items = (JSONArray) data.get("items");

            for (Object item : items) {
                BlogVO blog = createBlog((JSONObject) item);
                blogs.add(blog);
            }
        } else hookUtil.send_Embed_Hook(
            HookLevel.WARN,
            "블로그 목록 조회 실패",
            String.format(
                "Function: %s > %s\n%s",
                getClass().getName(),
                "getBlogList",
                String.format(
                    "bookname: %s",
                    bookname
                )
            )
        );
        return blogs;
    }

    private BlogVO createBlog(JSONObject jsonObject) {
        BlogVO blog = new BlogVO();

        blog.setTitle((String) jsonObject.get("title"));
        blog.setLink((String) jsonObject.get("link"));
        blog.setDescription((String) jsonObject.get("description"));

        return blog;
    }
}
