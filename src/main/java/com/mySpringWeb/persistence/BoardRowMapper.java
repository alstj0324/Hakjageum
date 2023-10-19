package com.mySpringWeb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.mySpringWeb.domain.BoardVO;

public class BoardRowMapper implements RowMapper<BoardVO>{

	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO board = new BoardVO();
//		����
		board.setBoard_id(rs.getInt("board_id"));
		board.setBoard_type(rs.getInt("board_type"));
		board.setTitle(rs.getString("title"));
		board.setContent(rs.getString("content"));
		board.setWriter_id(rs.getString("writer_id"));
		board.setBook_id(rs.getString("book_id"));
		board.setHobby_category(rs.getString("hobby_category"));
		board.setCreate_at(rs.getDate("create_at"));
		board.setUpdate_at(rs.getDate("update_at"));
		return board;
	}

}
