package com.mySpringWeb.controller.api;

import com.mySpringWeb.service.BasketService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/basket")
public class BasketAPI {

    @Autowired
    private BasketService basketService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<JSONArray> getBasketList(@PathVariable("userId") String userId) {
        JSONArray arr = basketService.gatBasketList(userId);
        return ResponseEntity.ok(arr);
    }

    @GetMapping("/add/{userId}/{bookId}")
    public ResponseEntity<JSONObject> addBook(@PathVariable("userId") String userId, @PathVariable("bookId") String bookId) {
        JSONObject res = new JSONObject();
        res.put("result", "success");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{userId}/{bookId}")
    public ResponseEntity<JSONObject> deleteBook(@PathVariable("userId") String userId, @PathVariable("bookId") String bookId) {
        JSONObject res = new JSONObject();
        res.put("result", "success");
        return ResponseEntity.ok(res);
    }
}
