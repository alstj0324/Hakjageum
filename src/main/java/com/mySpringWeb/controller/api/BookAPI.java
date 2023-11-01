package com.mySpringWeb.controller.api;

import com.mySpringWeb.domain.bookrecommend.BookVO;
import com.mySpringWeb.domain.webhook.HookLevel;
import com.mySpringWeb.utils.BookUtil;
import com.mySpringWeb.utils.HookUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookAPI {
    private final HookUtil hookUtil = new HookUtil();

    @GetMapping("/getlist")
    public ResponseEntity<List<BookVO>> getBookList(@RequestParam String category) {
        BookUtil bookUtil = new BookUtil();
        List<BookVO> bookList = bookUtil.getBookList(category);

        hookUtil.send_Embed_Hook(
            HookLevel.INFO,
            "Book 목록 조회",
            String.format(
                "category: %s",
                category
            )
        );

        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/getbybookid")
    public ResponseEntity<List<BookVO>> getBookInfo_id(@RequestParam String bookId) {
        BookUtil bookUtil = new BookUtil();
        List<BookVO> bookList = bookUtil.getBookInfo_id(bookId, 1, 1);

        hookUtil.send_Embed_Hook(
            HookLevel.INFO,
            "Book 정보 조회",
            String.format(
                "bookId: %s",
                bookId
            )
        );

        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/getbybookname")
    public ResponseEntity<List<BookVO>> getBookInfo_name(@RequestParam String bookName) {
        BookUtil bookUtil = new BookUtil();
        List<BookVO> bookList = bookUtil.getBookInfo_name(bookName, 1, 1);

        hookUtil.send_Embed_Hook(
            HookLevel.INFO,
            "Book 정보 조회",
            String.format(
                "bookName: %s",
                bookName
            )
        );

        return ResponseEntity.ok(bookList);
    }
}
