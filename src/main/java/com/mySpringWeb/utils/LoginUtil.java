package com.mySpringWeb.utils;

import com.mySpringWeb.domain.RequestType;
import com.mySpringWeb.domain.user.UserVO;
import com.mySpringWeb.domain.webhook.HookLevel;
import org.json.simple.JSONObject;

import java.util.*;

public class LoginUtil {
    private final HookUtil hookUtil = new HookUtil();

    public String getNaverToken(String code, String state) {
        String token = "";
        EnvUtil envUtil = new EnvUtil();
        RequestUtil requestUtil = new RequestUtil();
        Map<String, Object> params = new HashMap<>();

        String clientId = envUtil.getValueByKey("NAVER_CLIENTID");
        String secretId = envUtil.getValueByKey("NAVER_SECRETID");
        String redirectUrl = envUtil.getValueByKey("NAVER_LOGIN_REDIRECT");

        params.put("grant_type", "authorization_code");
        params.put("client_id", clientId);
        params.put("client_secret", secretId);
        params.put("redirect_uri", redirectUrl);
        params.put("code", code);
        params.put("state", state);

        JSONObject result = requestUtil.requestData(RequestType.NAVER_LOGINTOKEN, "GET", null, params);

        String status = (String) result.get("result_status");

        if (Objects.equals(status, "success")) {
            JSONObject data = (JSONObject) result.get("result_data");
            token = (String) data.get("access_token");
        } else hookUtil.send_Embed_Hook(
            HookLevel.WARN,
            "네이버 로그인 토큰 조회 실패",
            String.format(
                "Function: %s > %s\n%s",
                getClass().getName(),
                "getNaverToken",
                String.format(
                    "code: %s\nstate: %s",
                    code,
                    state
                )
            )
        );

        return token;
    }

    public UserVO getNaverUserInfo(String access_token) {
        UserVO user = new UserVO();
        EnvUtil envUtil = new EnvUtil();
        RequestUtil requestUtil = new RequestUtil();
        Map<String, Object> headers = new HashMap<>();

        headers.put("Authorization", "Bearer "+access_token);

        JSONObject result = requestUtil.requestData(RequestType.NAVER_LOGININFO, "POST", headers, null);

        String status = (String) result.get("result_status");

        if (Objects.equals(status, "success")) {
            JSONObject data = (JSONObject) result.get("result_data");
            JSONObject item = (JSONObject) data.get("response");

            user = createUser(item, "naver");
        } else hookUtil.send_Embed_Hook(
            HookLevel.WARN,
            "네이버 로그인 정보 조회 실패",
            String.format(
                "Function: %s > %s\n%s",
                getClass().getName(),
                "getNaverUserInfo",
                String.format(
                    "access_token: %s",
                    access_token
                )
            )
        );

        return user;
    }

    public String getKakaoToken(String code) {
        String token = "";
        EnvUtil envUtil = new EnvUtil();
        RequestUtil requestUtil = new RequestUtil();
        Map<String, Object> headers = new HashMap<>();
        Map<String, Object> params = new HashMap<>();

        String restapiKey = envUtil.getValueByKey("KAKAO_RESTKEY");
        String redirectUrl = envUtil.getValueByKey("KAKAO_LOGIN_REDIRECT");

        headers.put("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        params.put("grant_type", "authorization_code");
        params.put("client_id", restapiKey);
        params.put("redirect_uri", redirectUrl);
        params.put("code", code);

        JSONObject result = requestUtil.requestData(RequestType.KAKAO_LOGINTOKEN, "GET", null, params);

        String status = (String) result.get("result_status");

        if (Objects.equals(status, "success")) {
            JSONObject data = (JSONObject) result.get("result_data");
            token = (String) data.get("access_token");
        } else hookUtil.send_Embed_Hook(
            HookLevel.WARN,
            "카카오 로그인 토큰 조회 실패",
            String.format(
                "Function: %s > %s\n%s",
                getClass().getName(),
                "getKakaoToken",
                String.format(
                        "code: %s",
                        code
                )
            )
        );

        return token;
    }

    public UserVO getKakaoUserInfo(String access_token) {
        UserVO user = new UserVO();
        EnvUtil envUtil = new EnvUtil();
        RequestUtil requestUtil = new RequestUtil();
        Map<String, Object> headers = new HashMap<>();

        headers.put("Authorization", "Bearer " + access_token);
        headers.put("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        JSONObject result = requestUtil.requestData(RequestType.KAKAO_LOGININFO, "POST", headers, null);

        String status = (String) result.get("result_status");

        if (Objects.equals(status, "success")) {
            JSONObject data = (JSONObject) result.get("result_data");
            JSONObject account = (JSONObject) data.get("kakao_account");
            JSONObject item = (JSONObject) account.get("profile");

            user = createUser(item, "kakao");
        } else hookUtil.send_Embed_Hook(
                HookLevel.WARN,
                "카카오 로그인 정보 조회 실패",
                String.format(
                        "Function: %s > %s\n%s",
                        getClass().getName(),
                        "getKakaoUserInfo",
                        String.format(
                                "access_token: %s",
                                access_token
                        )
                )
        );

        return user;
    }

    private UserVO createUser(JSONObject jsonObject, String provider) {
        UserVO user = new UserVO();

        user.setId((String) jsonObject.get("id"));
        user.setEmail((String) jsonObject.get("email"));
        user.setPwd("null");
        user.setNickname((String) jsonObject.get("nickname"));
        user.setProvider(provider);

        return user;
    }
}
