package com.mySpringWeb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mySpringWeb.domain.bookrecommend.BasketVO;

public class BasketRowMapper implements RowMapper<BasketVO>{

	@Override
	public BasketVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BasketVO basket = new BasketVO();
		basket.setUser_id(rs.getString("user_id"));
		basket.setBook_unique_id(rs.getString("book_unique_id"));
		return basket;
	}

}
