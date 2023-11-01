package com.mySpringWeb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.mySpringWeb.domain.board.CommentVO;

public class CommentRowMapper implements RowMapper<CommentVO>{

	@Override
	public CommentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CommentVO comment = new CommentVO();
		comment.setComment_id(rs.getInt("comment_id"));
		comment.setPost_id(rs.getInt("post_id"));
		comment.setContent(rs.getString("content"));
		comment.setNickname(rs.getString("nickname"));
		comment.setWriter_id(rs.getString("writer_id"));
		comment.setCreated_at(rs.getDate("created_at"));
		comment.setDeleted_at(rs.getDate("deleted_at"));
		return comment;
	}

}
