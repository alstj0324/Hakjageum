package com.mySpringWeb.controller.api;

import com.mySpringWeb.service.BasketService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/basket")
public class BasketApi {

    @Autowired
    private BasketService basketService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<JSONArray> getBook(@PathVariable("userId") String userId) {
        JSONArray arr = basketService.gatBasketList(userId);
        System.out.println(arr);
        return ResponseEntity.ok(arr);
    }

    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<JSONObject> addBook(@PathVariable("userId") String userId, @PathVariable("bookId") String bookId) {
        JSONObject res = new JSONObject();
        res.put("result", "success");
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{userId}/{bookId}")
    public ResponseEntity<JSONObject> deleteBook(@PathVariable("userId") String userId, @PathVariable("bookId") String bookId) {
        JSONObject res = new JSONObject();
        res.put("result", "success");
        return ResponseEntity.ok(res);
    }
}
