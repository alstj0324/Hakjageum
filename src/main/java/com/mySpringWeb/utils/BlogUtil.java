package com.mySpringWeb.utils;

import com.mySpringWeb.domain.BlogVO;
import com.mySpringWeb.domain.BookVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.ui.Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class BlogUtil {
    public void getBlogList(String category, Model model) throws UnsupportedEncodingException {
        getBlogList(category, model, 1, 10);
    }

    public void getBlogList(String blog, Model model, int start, int display) throws UnsupportedEncodingException {// UTF-8 Encoding
        String clientId = "XSL_8Ps7NFtNXXxfpzVY";
        String clientSecret = "eLE8nAIerK";

        blog = URLEncoder.encode(blog + " 리뷰", "UTF-8");

        String apiHost = "https://openapi.naver.com/v1/search/blog.json";
        String apiURL = String.format(
                "%s?query=%s&start=%s&display=%s&sort=sim",
                apiHost, blog, start, display
        );

        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            int responseCode = con.getResponseCode();

            if (responseCode == 200) System.out.println("블로그 검색 API 정상");
            else System.out.println("블로그 검색 API 에러");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder res = new StringBuilder();

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                res.append(line);
            }
            br.close();

            if (responseCode == 200) {
                List<BlogVO> blogs = new ArrayList<>();
                JSONParser parsing = new JSONParser();

                Object obj = parsing.parse(res.toString());
                JSONObject jsonObj = (JSONObject) obj;
                JSONArray items = (JSONArray) jsonObj.get("items");

                for (Object item : items) {
                    blogs.add(createBlog((JSONObject) item));
                }

                model.addAttribute("blogs", blogs);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private BlogVO createBlog(JSONObject jsonObject) {
        BlogVO blog = new BlogVO();

        blog.setTitle((String) jsonObject.get("title"));
        blog.setLink((String) jsonObject.get("link"));
        blog.setDescription((String) jsonObject.get("description"));

        return blog;
    }
}
