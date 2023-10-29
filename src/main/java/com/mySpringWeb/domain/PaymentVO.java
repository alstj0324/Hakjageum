package com.mySpringWeb.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaymentVO {
	private String tid; // 카카오페이에서 넘어오는 결제당 고유값
	private String user_id; // 유저를 구별할수있는 id
	private Date paytime; // 결제 승인 시간 
	
	public String toString() {
		return String.format(
			"PaymentVO[\n" +
			"\ttid=%s\n" +
			"\tuser_id=%s\n" +
			"\tpayment=%s\n" +
			"]",
			tid, user_id, paytime
		);
	}
}
