package com.mySpringWeb.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.mySpringWeb.domain.CafeVO;

@Repository
public class CafeDAOSpring {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	private final String CAFE_GET = "select * from cafe_data where address=?";
	private final String CAFE_LIST = "select * from cafe_data";
	
	public CafeVO getCafe(CafeVO vo) {
		System.out.println("===> Spring JDBC로 getCafe() 기능처리");
		Object [] args  = {vo.getAddress()};
        return jdbctemplate.queryForObject(CAFE_GET, new CafeRowMapper(), args);
	}
	
	public List<CafeVO> getCafeList() {
		System.out.println("===> Spring JDBC로 getCafeList() 기능처리");
        return jdbctemplate.query(CAFE_LIST, new CafeRowMapper());
	}
}
