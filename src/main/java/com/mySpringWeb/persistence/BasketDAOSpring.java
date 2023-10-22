package com.mySpringWeb.persistence;

import com.mySpringWeb.service.BasketService;
import com.mySpringWeb.service.BoardService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@Repository
public class BasketDAOSpring {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	private final String BASKET_GETLIST = "select * from basket where user_id=? order by book_unique_id desc";

	public JSONArray getBasketList(String userId) {
		System.out.println("===> Spring JDBC로 getBasketList() 기능처리");
		Object [] args  = {userId};
		List<Map<String, Object>> basketList = jdbctemplate.queryForList(BASKET_GETLIST, args);
		JSONArray resArr = new JSONArray();

		for (int i = 0; i < basketList.size(); i++) {
			JSONObject res = new JSONObject();
			Map<String, Object> basket = basketList.get(i);

			res.put("basket_num", i);
			res.put("book_id", basket.get("book_unique_id"));
			resArr.add(res);
		}

		return resArr;
	}
}
