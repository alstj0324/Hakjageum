package com.mySpringWeb.service;

import org.json.simple.JSONArray;
import com.mySpringWeb.domain.BasketVO;

public interface BasketService {
  String checkBasket(BasketVO vo);
  void addBasket(BasketVO vo);
  void deleteBasket(BasketVO vo);
  JSONArray getBasketList(BasketVO vo);
  JSONArray getBasketList(String userId);
}
