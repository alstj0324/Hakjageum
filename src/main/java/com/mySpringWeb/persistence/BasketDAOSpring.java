package com.mySpringWeb.persistence;

import com.mySpringWeb.domain.BasketVO;
import com.mySpringWeb.domain.UserVO;
import com.mySpringWeb.service.BasketService;
import com.mySpringWeb.service.BoardService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	private String CHECK_BASKET = "select * from basket where user_id=? and book_unique_id=?";
	private String ADD_BASKET = "insert into basket(user_id,book_unique_id) values(?,?)";
	private String DELETE_BASKET = "delete from basket where user_id=? and book_unique_id=?";
	private final String BASKET_GETLIST = "select * from basket where user_id=? order by book_unique_id desc";
	
	
	public String checkBasket(BasketVO vo) {
		System.out.println("===>Spring JDBC로 checkBasket() 기능처리");
		System.out.println("여기는 BasketDAOSpring"+vo.getUser_id()+","+vo.getBook_unique_id());
		try {
            Object [] args  = {vo.getUser_id(),vo.getBook_unique_id()};
            jdbctemplate.queryForObject(CHECK_BASKET, new BasketRowMapper(), args);
            System.out.println("존재하는 책 입니다.");          
            return "False";
        }catch(EmptyResultDataAccessException e){
        	System.out.println("존재하지 않는 책 입니다."); 
            return "True";
        }
	}
	public void addBasket(BasketVO vo) {
        System.out.println("===>Spring JDBC로 addBasket() 기능처리");
        jdbctemplate.update(ADD_BASKET,vo.getUser_id(),vo.getBook_unique_id());                  
    }
	
	public void deleteBasket(BasketVO vo) {
		System.out.println("===>Spring JDBC로 deleteBasket() 기능처리");
		jdbctemplate.update(DELETE_BASKET, vo.getUser_id(), vo.getBook_unique_id());
	}

	public JSONArray getBasketList(BasketVO vo) {
		System.out.println("===> Spring JDBC로 getBasketList() 기능처리");
		Object [] args  = {vo.getUser_id()};
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
