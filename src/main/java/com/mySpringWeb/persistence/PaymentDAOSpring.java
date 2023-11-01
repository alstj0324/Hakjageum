package com.mySpringWeb.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mySpringWeb.domain.PaymentVO;

@Repository
public class PaymentDAOSpring {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	private final String PAYMENT_INSERT = "insert into payhistory(tid,user_id,amount,paytime) values(?,?,?,?)";
	private final String PAYMENT_DELETE = "delete from payhistory where tid=?";
	private final String PAYMENT_GETLIST = "select * from payhistory where user_id=? order by paytime desc";
	private final String PAYMENT_GETPAYTME = "select paytime from payhistory where tid=?";
	private final String PAYMENT_GETTOTALAMOUNT = "select sum(amount) as total_amount from payhistory where user_id=?";
	
	public void insertPayment(PaymentVO vo) {
		System.out.println("===>Spring JDBC로 insertPayment() 기능 처리");
		jdbctemplate.update(PAYMENT_INSERT, vo.getTid(), vo.getUser_id(), vo.getAmount(), vo.getPaytime());
	}
	public void deletePayment(PaymentVO vo) {
		System.out.println("===>Spring JDBC로 deletePayment() 기능 처리");
		System.out.println(vo);
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
	public String getPaytime(String tid) {
		System.out.println("===>Spring JDBC로 getPaytime() 기능 처리");
		Object [] args = {tid};
		return jdbctemplate.queryForObject(PAYMENT_GETPAYTME, args, String.class);
	}
	public int getTotalAmount(String userId) {
		System.out.println("===>Spring JDBC로 getTotalAmount() 기능 처리");
		Object [] args = {userId};
		int totalAmount = jdbctemplate.queryForObject(PAYMENT_GETTOTALAMOUNT, args, int.class);
		return totalAmount; 
	}
}	
