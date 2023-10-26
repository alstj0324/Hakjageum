package com.mySpringWeb.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mySpringWeb.domain.BasketVO;

@Repository
public class BasketDAOSpring {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	private final String BASKET_LIST = "select * from basket where user_id=?";
	
	public List<BasketVO> getBasketList(BasketVO vo) {
		System.out.println("===>Spring JDBC로 getBasketList() 기능처리");
		try {
            Object [] args  = {vo.getUser_id()};
            System.out.println(args);
            return jdbctemplate.query(BASKET_LIST, args, new BasketRowMapper());
        }catch(EmptyResultDataAccessException e){
            return null;
        }
	}
}
