package com.mySpringWeb.service;

import java.util.List;

import com.mySpringWeb.domain.BasketVO;

public interface BasketService {
	public List<BasketVO> getBasketList(BasketVO vo);
}
