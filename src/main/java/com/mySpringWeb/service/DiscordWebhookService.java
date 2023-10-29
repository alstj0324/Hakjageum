package com.mySpringWeb.service;

import com.mySpringWeb.domain.webhook.HookVO;

public interface DiscordWebhookService {
    void sendWebhook(HookVO hookVO);
}
