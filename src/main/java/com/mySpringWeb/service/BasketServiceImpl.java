package com.mySpringWeb.service;

import com.mySpringWeb.domain.BasketVO;
import com.mySpringWeb.persistence.BasketDAOSpring;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("basketService")
public class BasketServiceImpl implements BasketService {
	@Autowired 
	private BasketDAOSpring basketDAO;

	@Override
	public String checkBasket(BasketVO vo) {
		return basketDAO.checkBasket(vo);
	}
	@Override
	public void addBasket(BasketVO vo) {
		basketDAO.addBasket(vo); 
	}
	@Override
	public void deleteBasket(BasketVO vo) {
		basketDAO.deleteBasket(vo);
	}
	public String checkBasketList(String userId) {
		return basketDAO.checkBasketList(userId);
	}
	@Override
	public JSONArray getBasketList(BasketVO vo) {
		return basketDAO.getBasketList(vo);
	}
	@Override
	public JSONArray getBasketList(String userId) {
		return basketDAO.getBasketList(userId);
  }	
}
