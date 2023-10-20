package com.mySpringWeb.utils;

import com.mySpringWeb.domain.UserVO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class LoginUtil {
    public String getNaverToken(String code, String state) throws UnsupportedEncodingException {
        String token = "";
        String clientId = "XSL_8Ps7NFtNXXxfpzVY";
        String clientSecret = "eLE8nAIerK";

        String redirectURI = URLEncoder.encode("/biz/navercallback.do", "UTF-8");

        String apiHost = "https://nid.naver.com/oauth2.0/token";
        String apiURL = String.format(
                "%s?grant_type=authorization_code&client_id=%s&client_secret=%s&redirect_uri=%s&code=%s&state=%s",
                apiHost, clientId, clientSecret, redirectURI, code, state
        );

        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            if (responseCode == 200) System.out.println("로그인 토큰 API 정상");
            else System.out.println("로그인 토큰 API 에러");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder res = new StringBuilder();

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                res.append(line);
            }
            br.close();
            if (responseCode == 200) {
                List<String> tokens = new ArrayList<>();
                JSONParser parsing = new JSONParser();

                Object obj = parsing.parse(res.toString());
                JSONObject jsonObj = (JSONObject)obj;

                token = (String) jsonObj.get("access_token");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return token;
    }

    public void getNaverUserInfo(UserVO vo, String access_token) {
        String apiURL = "https://openapi.naver.com/v1/nid/me";

        try{
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer "+access_token);

            int responseCode = con.getResponseCode();

            if (responseCode == 200) System.out.println("로그인 정보 API 정상");
            else System.out.println("로그인 정보 API 에러");

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
                JSONObject item = (JSONObject)jsonObj.get("response");

                createUser(vo, item);
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private void createUser(UserVO user, JSONObject jsonObject) {
        user.setId((String) jsonObject.get("id"));
        user.setEmail((String) jsonObject.get("email"));
        user.setPwd("null");
        user.setNickname((String) jsonObject.get("nickname"));
        user.setProvider("naver");
    }
}