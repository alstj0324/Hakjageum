package com.mySpringWeb.service;

import java.util.List;

import com.mySpringWeb.domain.CafeVO;


public interface CafeService {
	public CafeVO getCafe(CafeVO vo);
	public List<CafeVO> getCafeList();
}
