package com.mySpringWeb.service;

import org.json.simple.JSONArray;
import com.mySpringWeb.domain.bookrecommend.BasketVO;

public interface BasketService {
  boolean checkBasket(BasketVO vo);
  void addBasket(BasketVO vo);
  void deleteBasket(BasketVO vo);
  JSONArray getBasketList(BasketVO vo);
  JSONArray getBasketList(String userId);
}
