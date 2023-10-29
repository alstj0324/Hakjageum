package com.mySpringWeb.service;

import com.mySpringWeb.persistence.BasketDAOSpring;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("basketService")
public class BasketServiceImpl implements BasketService {
	@Autowired 
	private BasketDAOSpring basketDAO;

	@Override
	public JSONArray getBasketList(String userId) {
		return basketDAO.getBasketList(userId);
	}
}
