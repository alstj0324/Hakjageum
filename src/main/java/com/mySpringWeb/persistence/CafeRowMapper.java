package com.mySpringWeb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.mySpringWeb.domain.CafeVO;

public class CafeRowMapper implements RowMapper<CafeVO>{
	@Override
	public CafeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CafeVO cafe = new CafeVO();

		cafe.setName(rs.getString("name"));
		cafe.setAddress(rs.getString("address"));
		return cafe;
	}

}


