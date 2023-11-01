package com.mySpringWeb.controller.api;

import com.mySpringWeb.domain.bookrecommend.BlogVO;
import com.mySpringWeb.domain.webhook.HookLevel;
import com.mySpringWeb.utils.BlogUtil;
import com.mySpringWeb.utils.HookUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogAPI {
    private final HookUtil hookUtil = new HookUtil();

    @GetMapping("/get")
    public ResponseEntity<List<BlogVO>> getBlog_bookname(@RequestParam String bookName) {
        BlogUtil blogUtil = new BlogUtil();
        List<BlogVO> blogList = blogUtil.getBlogList(bookName);

        hookUtil.send_Embed_Hook(
            HookLevel.INFO,
            "Blog 목록 조회",
            String.format(
                "bookName: %s",
                bookName
            )
        );

        return ResponseEntity.ok(blogList);
    }
}
