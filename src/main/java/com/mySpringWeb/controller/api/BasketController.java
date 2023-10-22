package com.mySpringWeb.controller.api;

import com.mySpringWeb.persistence.BasketDAOSpring;
import com.mySpringWeb.service.BasketService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<JSONArray> getBook(@PathVariable("userId") String userId) {
        JSONArray arr = basketService.gatBasketList(userId);
        return ResponseEntity.ok(arr);
    }

    @PostMapping("/add/{userId}/{bookId}")
    public ResponseEntity<JSONObject> addBook(@PathVariable("userId") String userId, @PathVariable("bookId") String bookId) {
        JSONObject res = new JSONObject();
        res.put("result", "success");
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("delete/{userId}/{bookId}")
    public ResponseEntity<JSONObject> deleteBook(@PathVariable("userId") String userId, @PathVariable("bookId") String bookId) {
        JSONObject res = new JSONObject();
        res.put("result", "success");
        return ResponseEntity.ok(res);
    }
}
