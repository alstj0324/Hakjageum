package com.mySpringWeb.controller.api;

import com.mySpringWeb.domain.BookVO;
import com.mySpringWeb.utils.BookUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookAPI {

    @GetMapping("/get/{bookId}")
    public ResponseEntity<List<BookVO>> getBookInfo(@PathVariable("bookId") String bookId) {
        BookUtil bookUtil = new BookUtil();
        List<BookVO> booklist = bookUtil.getBookInfo(bookId);
        return ResponseEntity.ok(booklist);
    }
}
