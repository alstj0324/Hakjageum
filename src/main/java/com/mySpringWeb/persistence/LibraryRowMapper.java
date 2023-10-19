package com.mySpringWeb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mySpringWeb.domain.LibraryVO;

public class LibraryRowMapper implements RowMapper<LibraryVO>{

	@Override
	public LibraryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		LibraryVO library = new LibraryVO();
		
		library.setName(rs.getString("name"));
		library.setAddress(rs.getString("address"));
		library.setDetail_address(rs.getString("detail_address"));
		library.setOperating_time(rs.getString("operating_time"));
		library.setClosed(rs.getString("closed"));
		library.setLatitude(rs.getString("latitude"));
		library.setLongitude(rs.getString("longitude"));
		
		return library;
	}
	
}
