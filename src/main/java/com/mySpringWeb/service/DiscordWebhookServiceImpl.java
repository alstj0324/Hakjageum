package com.mySpringWeb.service;

import com.mySpringWeb.domain.webhook.HookVO;

import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.URL;

public class DiscordWebhookServiceImpl implements DiscordWebhookService {

    @Override
    public void sendWebhook(HookVO hookVO) {
        try {
            URL url = new URL(hookVO.getUrl());
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            OutputStream stream = connection.getOutputStream();
            stream.write(hookVO.toJSON().toString().getBytes());
            stream.flush();
            stream.close();

            connection.getInputStream().close();
            connection.disconnect();
        } catch (Exception e) {
            System.out.println("웹훅을 전송할 수 없습니다.");
            e.printStackTrace();
        }
    }
}
