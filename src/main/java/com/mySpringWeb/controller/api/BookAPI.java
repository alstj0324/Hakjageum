package com.mySpringWeb.controller.api;

import com.mySpringWeb.domain.BookVO;
import com.mySpringWeb.domain.webhook.EmbedVO;
import com.mySpringWeb.domain.webhook.HookLevel;
import com.mySpringWeb.domain.webhook.HookVO;
import com.mySpringWeb.service.DiscordWebhookServiceImpl;
import com.mySpringWeb.utils.BookUtil;
import com.mySpringWeb.utils.HookUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookAPI {

    /*
        GET /api/book/get?bookId={bookId}
     */

    @GetMapping("/get")
    public ResponseEntity<List<BookVO>> getBookInfo(@RequestParam String bookId) {
        BookUtil bookUtil = new BookUtil();
        List<BookVO> bookList = bookUtil.getBookInfo(bookId);

        DiscordWebhookServiceImpl discordWebhookService = new DiscordWebhookServiceImpl();
        List<EmbedVO> embedList = new ArrayList<>();
        HookUtil hookUtil = new HookUtil();
        EmbedVO embedVO = hookUtil.Info_Embed("Book 조회", "bookId: " + bookId + "\nData:\n" + bookList.toString());
        embedList.add(embedVO);
        HookVO hookVO = hookUtil.create_Hook (HookLevel.INFO, embedList);
        discordWebhookService.sendWebhook(hookVO);

        return ResponseEntity.ok(bookList);
    }
}
