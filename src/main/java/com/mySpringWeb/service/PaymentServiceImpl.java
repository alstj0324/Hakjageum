package com.mySpringWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mySpringWeb.domain.PaymentVO;
import com.mySpringWeb.persistence.PaymentDAOSpring;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentDAOSpring paymentDAO;
	
	@Override
	public void insertPayment(PaymentVO vo) {
		paymentDAO.insertPayment(vo);
	}
	public void deletePayment(PaymentVO vo) {
		paymentDAO.deletePayment(vo);
	}
	public List<PaymentVO> getPaymentList() {
		return paymentDAO.getPaymentList();
	}
}