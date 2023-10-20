package com.mySpringWeb.utils;

import com.mySpringWeb.domain.UserVO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
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
            if (responseCode == 200) System.out.println("네이버 로그인 토큰 API 정상");
            else System.out.println("네이버 로그인 토큰 API 에러");

            StringBuilder res = get_responce(con);
            if (responseCode == 200) token = get_token(res);
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
            if (responseCode == 200) System.out.println("네이버 로그인 정보 API 정상");
            else System.out.println("네이버 로그인 정보 API 에러");

            StringBuilder res = get_responce(con);

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

    public String getKakaoToken(String code) throws UnsupportedEncodingException {
        String token = "";
        String clientId = "bbbc3bb1c878a2317fd7f89dec646ea9";
        String redirectURI = URLEncoder.encode("http://localhost:8080/biz/kakaocallback.do", "UTF-8");

        String apiHost = "https://kauth.kakao.com/oauth/token";
        String apiURL = String.format(
                "%s?grant_type=authorization_code&client_id=%s&redirect_uri=%s&code=%s",
                apiHost, clientId, redirectURI, code
        );

        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            int responseCode = con.getResponseCode();
            if (responseCode == 200) System.out.println("카카오 로그인 토큰 API 정상");
            else System.out.println("카카오 로그인 토큰 API 에러");

            StringBuilder res = get_responce(con);

            if (responseCode == 200) token = get_token(res);
        } catch (Exception e) {
            System.out.println(e);
        }

        return token;
    }


    public void getKakaoUserInfo(UserVO vo, String access_token) {
        String apiURL = "https://kapi.kakao.com/v2/user/me";

        try{
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + access_token);
            con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            int responseCode = con.getResponseCode();
            if (responseCode == 200) System.out.println("카카오 로그인 정보 API 정상");
            else System.out.println("네이버 카카오 정보 API 에러");

            StringBuilder res = get_responce(con);

            if (responseCode == 200) {
                JSONParser parsing = new JSONParser();

                Object obj = parsing.parse(res.toString());
                JSONObject jsonObj = (JSONObject)obj;
                JSONObject accountObj = (JSONObject)jsonObj.get("kakao_account");
                JSONObject item = (JSONObject)accountObj.get("profile");

                createUser(vo, item);
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private String get_token(StringBuilder res) throws ParseException {
        String token = "";
        try {
            JSONParser parsing = new JSONParser();

            Object obj = parsing.parse(res.toString());
            JSONObject jsonObj = (JSONObject)obj;

            token = (String) jsonObj.get("access_token");
        } catch (Exception e) {
            System.out.println(e);
        }

        return token;
    }

    private StringBuilder get_responce(HttpURLConnection con) {
        StringBuilder res = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                res.append(line);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return res;
    }
    private void createUser(UserVO user, JSONObject jsonObject) {
        user.setId((String) jsonObject.get("id"));
        user.setEmail((String) jsonObject.get("email"));
        user.setPwd("null");
        user.setNickname((String) jsonObject.get("nickname"));
        user.setProvider("naver");
    }
}
