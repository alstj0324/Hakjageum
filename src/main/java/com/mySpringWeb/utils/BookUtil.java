package com.mySpringWeb.utils;

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

public class BookUtil {
    public void getBookList(String category, Model model) throws UnsupportedEncodingException {
        getBookList(category, model, 1, 100);
    }

    public List<BookVO> getBookInfo(String bookId) {
        List<BookVO> books = new ArrayList<>();
        String clientId = "XSL_8Ps7NFtNXXxfpzVY";
        String clientSecret = "eLE8nAIerK";

        try {
            bookId = URLEncoder.encode(bookId, "UTF-8");

            String apiHost = "https://openapi.naver.com/v1/search/book_adv.json";
            String apiURL = String.format(
                    "%s?d_isbn=%s",
                    apiHost, bookId
            );

            // 네이버 검색 API 요청
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            int responseCode = con.getResponseCode();

            if (responseCode == 200) System.out.println("도서 검색 API 정상");
            else System.out.println("도서 검색 API 에러");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder res = new StringBuilder();

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                res.append(line);
            }
            br.close();
            if (responseCode == 200) {
                JSONParser parsing = new JSONParser();

                Object obj = parsing.parse(res.toString());
                JSONObject jsonObj = (JSONObject)obj;
                JSONArray items = (JSONArray) jsonObj.get("items");
                
                for (Object item : items) {
                    BookVO book = createBook((JSONObject) item);
                    books.add(book);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return books;
    }

    public void getBookList(String category, Model model, int start, int display) throws UnsupportedEncodingException {
        String clientId = "XSL_8Ps7NFtNXXxfpzVY";
        String clientSecret = "eLE8nAIerK";

        category = URLEncoder.encode(category, "UTF-8");

        String apiHost = "https://openapi.naver.com/v1/search/book.json";
        String apiURL = String.format(
                "%s?query=%s&start=%s&display=%s&sort=sim",
                apiHost, category, start, display
        );

        // 네이버 검색 API 요청
        try{
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            int responseCode = con.getResponseCode();

            if (responseCode == 200) System.out.println("도서 검색 API 정상");
            else System.out.println("도서 검색 API 에러");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder res = new StringBuilder();

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                res.append(line);
            }
            br.close();
            if (responseCode == 200) {
                List<BookVO> books = new ArrayList<>();
                JSONParser parsing = new JSONParser();

                Object obj = parsing.parse(res.toString());
                JSONObject jsonObj = (JSONObject)obj;
                JSONArray items = (JSONArray)jsonObj.get("items");

                for (Object item : items) {
                    books.add(createBook((JSONObject) item));
                }
                model.addAttribute("books", books);
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }

    private BookVO createBook(JSONObject jsonObject) {
        BookVO book = new BookVO();

        book.setTitle((String) jsonObject.get("title"));
        book.setAuthor((String) jsonObject.get("author"));
        book.setLink((String) jsonObject.get("link"));
        book.setImage((String) jsonObject.get("image"));
        book.setPubdate((String) jsonObject.get("pubdate"));
        book.setIsbn((String) jsonObject.get("isbn"));
        String description = (String)jsonObject.get("description");
        description = description.replaceAll("\"", "'");
        book.setDescription(description);
        book.setDiscount((String) jsonObject.get("discount"));
        book.setPublisher((String) jsonObject.get("publisher"));

        return book;
    }
}
