package com.mySpringWeb.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.mySpringWeb.domain.LibraryVO;

@Repository
public class LibraryDAOSpring {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	private final String LIBRARY_GET = "select * from library_data where address=?";
	private final String LIBRARY_LIST = "select * from library_data";
	
	public LibraryVO getLibrary(LibraryVO vo) {
		System.out.println("===> Spring JDBC로 getLibrary() 기능처리");
		Object [] args  = {vo.getAddress()};
        return jdbctemplate.queryForObject(LIBRARY_GET, new LibraryRowMapper(), args);
	}
	
	public List<LibraryVO> getLibraryList() {
		System.out.println("===> Spring JDBC로 getLibraryList() 기능처리");
        return jdbctemplate.query(LIBRARY_LIST, new LibraryRowMapper());
	}
}
