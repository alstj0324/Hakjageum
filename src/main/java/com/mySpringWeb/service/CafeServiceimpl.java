package com.mySpringWeb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mySpringWeb.domain.CafeVO;
import com.mySpringWeb.persistence.CafeDAOSpring;

@Service("cafeService")
public class CafeServiceimpl implements CafeService{
	@Autowired
	private CafeDAOSpring cafeDAO;

	@Override
	public CafeVO getCafe(CafeVO vo) {
		return cafeDAO.getCafe(vo);
	}	
	@Override
	public List<CafeVO> getCafeList() {
		return cafeDAO.getCafeList();
	}
}
