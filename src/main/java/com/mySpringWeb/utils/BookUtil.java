package com.mySpringWeb.utils;

import com.mySpringWeb.domain.bookrecommend.BookVO;
import com.mySpringWeb.domain.RequestType;
import com.mySpringWeb.domain.webhook.HookLevel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class BookUtil {
    private final HookUtil hookUtil = new HookUtil();

    public List<BookVO> getBookList(String category) {
        return getBookList(category, 1, 100);
    }

    public List<BookVO> getBookInfo(String category) {
        return getBookList(category, 1, 1);
    }

    public List<BookVO> getBookList(String category, int start, int display) {
        List<BookVO> books = new ArrayList<>();
        EnvUtil envUtil = new EnvUtil();
        String clientId = envUtil.getValueByKey("NAVER_CLIENTID");
        String secretId = envUtil.getValueByKey("NAVER_SECRETID");

        RequestUtil requestUtil = new RequestUtil();
        Map<String, Object> headers = new HashMap<>();
        headers.put("X-Naver-Client-Id", clientId);
        headers.put("X-Naver-Client-Secret", secretId);

        Map<String, Object> params = new HashMap<>();
        params.put("query", category);
        params.put("start", start);
        params.put("display", display);
        params.put("sort", "sim");

        JSONObject result = requestUtil.requestData(RequestType.NAVER_BOOKLIST_SEARCH, "GET", headers, params);

        String status = (String) result.get("result_status");

        if (Objects.equals(status, "success")) {
            JSONObject data = (JSONObject) result.get("result_data");
            JSONArray items = (JSONArray) data.get("items");

            for (Object item : items) {
                BookVO book = createBook((JSONObject) item);
                books.add(book);
            }
        } else hookUtil.send_Embed_Hook(
            HookLevel.WARN,
            "도서 목록 조회 실패",
            String.format(
                "Function: %s > %s\n%s",
                getClass().getName(),
                "getBookList",
                String.format(
                    "category: %s",
                    category
                )
            )
        );

        return books;
    }

    public List<BookVO> getBookInfo_name(String bookName, int start, int display) {
        EnvUtil envUtil = new EnvUtil();
        String clientId = envUtil.getValueByKey("NAVER_CLIENTID");
        String secretId = envUtil.getValueByKey("NAVER_SECRETID");

        RequestUtil requestUtil = new RequestUtil();

        Map<String, Object> headers = new HashMap<>();
        headers.put("X-Naver-Client-Id", clientId);
        headers.put("X-Naver-Client-Secret", secretId);

        Map<String, Object> params = new HashMap<>();
        params.put("d_titl", bookName);
        params.put("start", start);
        params.put("display", display);
        params.put("sort", "sim");

        JSONObject result = requestUtil.requestData(RequestType.NAVER_BOOK_SEARCH, "GET", headers, params);

        String status = (String) result.get("result_status");

        List<BookVO> books = new ArrayList<>();

        if (status == "success") {
            JSONObject data = (JSONObject) result.get("result_data");
            JSONArray items = (JSONArray) data.get("items");

            for (Object item : items) {
                BookVO book = createBook((JSONObject) item);
                books.add(book);
            }
        } else hookUtil.send_Embed_Hook(
                HookLevel.WARN,
                "도서 정보 조회 실패",
                String.format(
                        "Function: %s > %s\n%s",
                        getClass().getName(),
                        "getBookInfo",
                        String.format(
                                "bookName: %s",
                                bookName
                        )
                )
        );
        return books;
    }
    public List<BookVO> getBookInfo_id(String bookId, int start, int display) {
        EnvUtil envUtil = new EnvUtil();
        String clientId = envUtil.getValueByKey("NAVER_CLIENTID");
        String secretId = envUtil.getValueByKey("NAVER_SECRETID");

        RequestUtil requestUtil = new RequestUtil();

        Map<String, Object> headers = new HashMap<>();
        headers.put("X-Naver-Client-Id", clientId);
        headers.put("X-Naver-Client-Secret", secretId);

        Map<String, Object> params = new HashMap<>();
        params.put("d_isbn", bookId);
        params.put("start", start);
        params.put("display", display);
        params.put("sort", "sim");

        JSONObject result = requestUtil.requestData(RequestType.NAVER_BOOK_SEARCH, "GET", headers, params);

        String status = (String) result.get("result_status");

        List<BookVO> books = new ArrayList<>();

        if (status == "success") {
            JSONObject data = (JSONObject) result.get("result_data");
            JSONArray items = (JSONArray) data.get("items");

            for (Object item : items) {
                BookVO book = createBook((JSONObject) item);
                books.add(book);
            }
        } else hookUtil.send_Embed_Hook(
            HookLevel.WARN,
            "도서 정보 조회 실패",
            String.format(
                "Function: %s > %s\n%s",
                getClass().getName(),
                "getBookInfo",
                String.format(
                    "bookId: %s",
                    bookId
                )
            )
        );
        return books;
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
