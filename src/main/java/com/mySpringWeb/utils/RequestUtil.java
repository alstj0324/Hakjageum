package com.mySpringWeb.utils;

import com.mySpringWeb.domain.RequestType;
import com.mySpringWeb.domain.webhook.HookLevel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RequestUtil {
    private final HookUtil hookUtil = new HookUtil();

    public JSONObject requestData(RequestType type, String method, Map<String, Object> headers, Map<String, Object> params) {
        return requestData(type, method, headers, params, "");
    }

    public JSONObject requestData(RequestType type, String method, Map<String, Object> headers, Map<String, Object> params, String backurl) {
        JSONObject result = new JSONObject();
        try {
            String hostURL = type.getUrl();
            URL apiURL = new URL(createURL(hostURL, params) + backurl);

            HttpURLConnection con = (HttpURLConnection) apiURL.openConnection();
            createHeader(con, method, headers);

            int responseCode = con.getResponseCode();

            if (responseCode == 200) {
                hookUtil.send_Embed_Hook(
                    HookLevel.INFO,
                    "API 요청 성공",
                    String.format(
                        "요청타입 : %s\n응답코드 : %s",
                        type.name(), responseCode
                    )
                );
                result.put("result_status", "success");
            } else {
                hookUtil.send_Embed_Hook(
                    HookLevel.DANGER,
                    "API 요청 실패",
                    String.format(
                        "요청타입 : %s\n응답코드 : %s",
                        type.name(), responseCode
                    )
                );
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder res = new StringBuilder();

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                res.append(line);
            }
            br.close();

            JSONParser parsing = new JSONParser();
            Object obj = parsing.parse(res.toString());
            result.put("result_data", obj);
        } catch (Exception e) {
            result.put("result_status", "fail");
            hookUtil.send_Embed_Hook(
                HookLevel.DANGER,
                "API 요청 실패",
                String.format(
                    "요청타입 : %s\nException 종류 : %s\n오류Message: %s",
                    type.name(), e.getCause(), e.getMessage()
                )
            );
            e.printStackTrace();
        }

        return result;
    }

    private String createURL(String hostURL, Map<String, Object> params) {
        StringBuilder url = new StringBuilder(hostURL);

        if (params == null || !params.isEmpty()) {
            url.append("?");
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (param.getValue() instanceof String) {
                    String value = "";
                    try {
                        value = URLEncoder.encode((String) param.getValue(), "UTF-8");
                    } catch (Exception e) {
                        hookUtil.send_Embed_Hook(
                            HookLevel.WARN,
                            "URL 인코딩 실패",
                            String.format(
                                "Function: %s > %s\n%s",
                                getClass().getName(),
                                "createURL",
                                String.format(
                                    "param: %s",
                                    param.getValue()
                                )
                            )
                        );
                    }
                    url.append(param.getKey()).append("=").append(value).append("&");
                } else {
                    url.append(param.getKey()).append("=").append(param.getValue()).append("&");
                }

            }
        }
        return url.toString();
    }

    private void createHeader(HttpURLConnection con, String method, Map<String, Object> headers) {
        try {
            con.setRequestMethod(method);

            if (headers == null || !headers.isEmpty()) {
                for (Map.Entry<String, Object> header : headers.entrySet()) {
                    con.setRequestProperty(header.getKey(), header.getValue().toString());
                }
            }
        } catch (Exception e) {
            hookUtil.send_Embed_Hook(
                HookLevel.WARN,
                "Header 생성 실패",
                String.format(
                    "Function: %s > %s\n%s",
                    getClass().getName(),
                    "createHeader",
                    String.format(
                        "method: %s\nheaders: %s",
                        method, headers
                    )
                )
            );
            e.printStackTrace();
        }
    }
}

