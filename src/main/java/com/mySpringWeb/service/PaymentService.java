package com.mySpringWeb.service;

import java.util.List;

import com.mySpringWeb.domain.PaymentVO;

public interface PaymentService {
	void insertPayment(PaymentVO vo);
	void deletePayment(PaymentVO vo);
	List<PaymentVO> getPaymentList(String userid);
	String getPaytime(String tid);
	int getTotalAmount(String user_id);
}
