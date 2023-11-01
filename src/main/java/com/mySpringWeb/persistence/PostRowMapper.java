package com.mySpringWeb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.mySpringWeb.domain.board.PostVO;

public class PostRowMapper implements RowMapper<PostVO>{

	@Override
	public PostVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		PostVO post = new PostVO();
		post.setId(rs.getInt("id"));
		post.setTitle(rs.getString("title"));
		post.setContent(rs.getString("content"));
		post.setBook_id(rs.getString("book_id"));
		post.setHobby_code(rs.getString("hobby_code"));
		post.setBoard_code(rs.getString("board_code"));
		post.setView_count(rs.getInt("view_count"));
		post.setWriter_id(rs.getString("writer_id"));
		post.setNickname(rs.getString("nickname"));
		post.setCreated_at(rs.getDate("created_at"));
		post.setUpdated_at(rs.getDate("updated_at"));
		post.setDeleted_at(rs.getDate("deleted_at"));
		return post;
	}

}
