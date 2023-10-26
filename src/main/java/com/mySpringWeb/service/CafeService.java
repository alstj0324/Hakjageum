package com.mySpringWeb.service;

import java.util.List;

import com.mySpringWeb.domain.CafeVO;


public interface CafeService {
	CafeVO getCafe(CafeVO vo);
	List<CafeVO> getCafeList();
}
