package com.mySpringWeb.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.mySpringWeb.domain.board.PostVO;

@Repository
public class PostDAOSpring {
	@Autowired
	private JdbcTemplate jdbctemplate;

	/*
		parms: board_code
	 */
	private final String POST_COUNT = "call get_postcount(?)";

	/*
		parms: title, content, board_code, hobby_code, writer_id, book_id
	 */
	private final String POST_INSERT = "call insert_post(?, ?, ?, ?, ?, ?)";
	/*
		parms: post_id, title, content
	 */
	private final String POST_UPDATE = "call update_post(?, ?, ?)";
	/*
		parms: post_id
	 */
	private final String POST_DELETE = "call delete_post(?)";
	/*
		parms: post_id
	 */
	private final String POST_VIEW_COUNT = "call inc_viewcount(?)";
	/*
		parms: post_id
	 */
	private final String POST_GET = "call get_post(?)";
	/*
		parms: board_code
	 */
	private final String POST_LIST = "call get_postlist(?)";

	public void insertBoard(PostVO vo) {
        System.out.println("===> Spring JDBC로 insertBoard() 기능처리");
        jdbctemplate.update(POST_INSERT,vo.getBoard_code(), vo.getTitle(), vo.getContent(), vo.getWriter_id(), vo.getHobby_code(), vo.getBook_id());
    }
	
	public void updateBoard(PostVO vo) {
		System.out.println("===> Spring JDBC로 updateBoard() 기능처리");
		System.out.println("결과값 :"+vo);
		jdbctemplate.update(POST_UPDATE, vo.getTitle(), vo.getContent(), vo.getId());
	}
	
	public void deleteBoard(String id) {
		System.out.println("===> Spring JDBC로 deleteBoard() 기능처리");
		jdbctemplate.update(POST_DELETE, id);
	}
	
	public void addCount(String id) {
		System.out.println("===> Spring JDBC로 addCount() 기능처리");
		jdbctemplate.update(POST_VIEW_COUNT, id);
	}
	
	public PostVO getBoard(String id) {
        System.out.println("===> Spring JDBC로 getBoard() 기능처리");
        Object [] args  = {id};
        return jdbctemplate.queryForObject(POST_GET, new PostRowMapper(), args);
    }
	
	public List<PostVO> getBoardList(String board_code) {
        System.out.println("===> Spring JDBC로 getBoardList() 기능처리");
        Object [] args  = {board_code};
        return jdbctemplate.query(POST_LIST, new PostRowMapper(), args);
    }
	
}
