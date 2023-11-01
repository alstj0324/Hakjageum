package com.mySpringWeb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.mySpringWeb.domain.user.UserVO;

public class UserRowMapper implements RowMapper<UserVO>{

	@Override
	public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserVO user = new UserVO();
		user.setId(rs.getString("id"));
		user.setPwd(rs.getString("pwd"));
		user.setNickname(rs.getString("nickname"));
		user.setEmail(rs.getString("email"));
		user.setProvider(rs.getString("provider"));
		user.setRole_id(rs.getInt("role_id"));
		user.setCreate_at(rs.getDate("create_at"));
		user.setPoint(rs.getInt("point"));
		return user;

	}
}
