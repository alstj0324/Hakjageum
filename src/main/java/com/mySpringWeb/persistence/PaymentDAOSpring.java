package com.mySpringWeb.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mySpringWeb.domain.pay.PaymentVO;

@Repository
public class PaymentDAOSpring {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	private final String PAYMENT_INSERT = "insert into payhistory(tid,user_id,amount,paytime) values(?,?,?,?)";
	private final String PAYMENT_DELETE = "delete from payhistory where tid=?";
	private final String PAYMENT_GETLIST = "select * from payhistory where user_id=? order by paytime desc";
	
	public void insertPayment(PaymentVO vo) {
		System.out.println("===>Spring JDBC로 insertPayment() 기능 처리");
		jdbctemplate.update(PAYMENT_INSERT, vo.getTid(), vo.getUser_id(), vo.getAmount(), vo.getPaytime());
	}
	public void deletePayment(PaymentVO vo) {
		System.out.println("===>Spring JDBC로 deletePayment() 기능 처리");
		Object [] args = {vo.getTid()};
		jdbctemplate.update(PAYMENT_DELETE, args);
	}
//	public List<PaymentVO> getPaymentList() {
//		System.out.println("===>Spring JDBC로 getPaymentListPayment() 기능 처리");
//		return jdbctemplate.query(PAYMENT_GETLIST, new PaymentRowMapper());
//	}
	public List<PaymentVO> getPaymentList(String userId) {
		System.out.println("===>Spring JDBC로 getPaymentListPayment() 기능 처리");
		Object [] args = {userId};
		return jdbctemplate.query(PAYMENT_GETLIST, args, new PaymentRowMapper());
	}
}	
