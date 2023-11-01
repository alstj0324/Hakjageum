package com.mySpringWeb.controller.api;

import com.mySpringWeb.domain.bookrecommend.BasketVO;
import com.mySpringWeb.domain.webhook.HookLevel;
import com.mySpringWeb.service.BasketService;
import com.mySpringWeb.utils.BookUtil;
import com.mySpringWeb.utils.HookUtil;
import com.mySpringWeb.utils.MappingUtil;
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

    private final HookUtil hookUtil = new HookUtil();
    private final MappingUtil mappingUtil = new MappingUtil();

    @GetMapping("/get")
    public ResponseEntity<JSONArray> getBasketList(@RequestParam String userId) {
        JSONArray arr = basketService.getBasketList(userId);

        hookUtil.send_Embed_Hook(
            HookLevel.INFO,
            "Basket 정보 조회",
            String.format(
                "UserId: %s",
                userId
            )
        );

        return ResponseEntity.ok(arr);
    }
    
    @GetMapping("/checkList")
    public ResponseEntity<String> new_checkBasketList(@RequestParam String userId) {
        String val = basketService.checkBasketList(userId);

        hookUtil.send_Embed_Hook(
            HookLevel.INFO,
            "Basket 정보 조회",
            String.format(
                "UserId: %s",
                userId
            )
        );

        return ResponseEntity.ok(val);
    }

    @GetMapping("/getbookinfo")
    public ResponseEntity<JSONArray> getBasketListToBookInfo(@RequestParam String userId) {
        JSONArray bookArr = new JSONArray();
        JSONArray arr = basketService.getBasketList(userId);
        BookUtil bookUtil = new BookUtil();

        for (Object item : arr) {
            JSONObject obj = (JSONObject) item;

            JSONArray jsonarr = mappingUtil.bookToObject(bookUtil.getBookInfo((String) obj.get("book_id")));
            if (!jsonarr.isEmpty()) bookArr.add(jsonarr.get(0));
        }

        hookUtil.send_Embed_Hook(
            HookLevel.INFO,
            "Basket 정보 조회 (BookInfo)",
            String.format(
                "UserId: %s",
                userId
            )
        );

        return ResponseEntity.ok(bookArr);
    }



    @GetMapping("/has")
    public ResponseEntity<Boolean> getBasketListToBookInfo(@RequestParam String userId, @RequestParam String bookId) {
        boolean res = basketService.checkBasket(new BasketVO(userId, bookId));

        hookUtil.send_Embed_Hook(
            HookLevel.INFO,
            "Basket 정보 조회 (BookInfo)",
            String.format(
                "UserId: %s\nBookId: %s\n",
                userId,
                bookId
            )
        );

        return ResponseEntity.ok(res);
    }

    @GetMapping("/add")
    public ResponseEntity<Boolean> addBasketData(@RequestParam String userId, @RequestParam String bookId) {
        BasketVO vo = new BasketVO(userId, bookId);
        if (!basketService.checkBasket(vo)) {
            basketService.addBasket(vo);

            hookUtil.send_Embed_Hook(
                HookLevel.INFO,
                "Basket 정보 추가",
                String.format(
                    "UserId: %s\nBookId: %s\n",
                    userId,
                    bookId
                )
            );
            return ResponseEntity.ok(true);
        } else return ResponseEntity.ok(false);
    }

    @GetMapping("/delete")
    public ResponseEntity<Boolean> deleteBasketData(@RequestParam String userId, @RequestParam String bookId) {
        BasketVO vo = new BasketVO(userId, bookId);
        System.out.println(vo);
        if (basketService.checkBasket(vo)) {
            basketService.deleteBasket(vo);

            System.out.println(vo);
            hookUtil.send_Embed_Hook(
                HookLevel.INFO,
                "Basket 정보 삭제",
                String.format(
                    "UserId: %s\nBookId: %s\n",
                    userId,
                    bookId
                )
            );
            return ResponseEntity.ok(true);
        } else return ResponseEntity.ok(false);
    }
}
