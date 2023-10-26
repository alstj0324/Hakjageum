package com.mySpringWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mySpringWeb.domain.BasketVO;
import com.mySpringWeb.persistence.BasketDAOSpring;

@Service("basketService")
public class BasketServiceImpl implements BasketService{
	@Autowired
	private BasketDAOSpring basketDAO;

	@Override
	public List<BasketVO> getBasketList(BasketVO vo) {
		return basketDAO.getBasketList(vo);
	}
}
