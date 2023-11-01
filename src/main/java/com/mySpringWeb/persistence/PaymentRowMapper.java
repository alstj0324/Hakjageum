package com.mySpringWeb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mySpringWeb.domain.pay.PaymentVO;

public class PaymentRowMapper implements RowMapper<PaymentVO> {
	
	@Override
	public PaymentVO mapRow(ResultSet rs, int rowNum) throws SQLException{
		PaymentVO payment = new PaymentVO();
		
		payment.setTid(rs.getString("tid"));
		payment.setUser_id(rs.getString("user_id"));
		payment.setAmount(rs.getInt("amount"));
		payment.setPaytype(rs.getString("paytype"));
		payment.setCreated_at(rs.getTimestamp("created_at"));
		
		return payment;
	}
}
