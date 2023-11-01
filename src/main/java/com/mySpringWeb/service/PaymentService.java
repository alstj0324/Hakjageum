package com.mySpringWeb.service;

import java.util.List;

import com.mySpringWeb.domain.pay.PaymentVO;

public interface PaymentService {
	void insertPayment(PaymentVO vo);
	void deletePayment(PaymentVO vo);
	List<PaymentVO> getPaymentList(String userid);
}
