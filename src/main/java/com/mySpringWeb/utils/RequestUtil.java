package com.mySpringWeb.utils;

public class RequestUtil {
    final String kakao_clientId = "c9e9586c57fe79bc3c0ee0c52ad1a6f2";
    final String kakao_adminKey = "3d847ad0fc68439f7949aa9254f80fc8";
    private final String naver_clientId = "XSL_8Ps7NFtNXXxfpzVY";
    private final String naver_secretId = "eLE8nAIerK";
    private final String GET_BOOK_LIST = "https://openapi.naver.com/v1/search/book.json/";
    private final String GET_BOOK_INFO = "https://openapi.naver.com/v1/search/book_adv.json/";
    private final String GET_PLACE_KEYWORD = "https://dapi.kakao.com/v2/local/search/keyword.json/";
    private final String GET_LOCATION = "http://ip-api.com/json/";
    private final String GET_BLOG_LIST = "https://openapi.naver.com/v1/search/blog.json/";

//    public JSONArray requestData(Map<String, String> headers, Map<String, String> params) {
//
//
//        category = URLEncoder.encode(category, "UTF-8");
//        String apiURL = String.format(
//                "%s?query=%s&start=%s&display=%s&sort=sim",
//                apiHost, category, start, display
//        );
//
//        // 네이버 검색 API 요청
//        try{
//            URL url = new URL(apiURL);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//
//            con.setRequestMethod("GET");
//            con.setRequestProperty("X-Naver-Client-Id", clientId);
//            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
//
//            int responseCode = con.getResponseCode();
//
//            if (responseCode == 200) System.out.println("도서 검색 API 정상");
//            else System.out.println("도서 검색 API 에러");
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            StringBuilder res = new StringBuilder();
//
//            for (String line = br.readLine(); line != null; line = br.readLine()) {
//                res.append(line);
//            }
//            br.close();
//            if (responseCode == 200) {
//                List<BookVO> books = new ArrayList<>();
//                JSONParser parsing = new JSONParser();
//
//                Object obj = parsing.parse(res.toString());
//                JSONObject jsonObj = (JSONObject)obj;
//                JSONArray items = (JSONArray)jsonObj.get("items");
//
//                for (Object item : items) {
//                    books.add(createBook((JSONObject) item));
//                }
//                model.addAttribute("books", books);
//            }
//        } catch(Exception e){
//            System.out.println(e);
//        }
//    }
}

