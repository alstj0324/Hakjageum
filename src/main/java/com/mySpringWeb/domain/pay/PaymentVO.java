package com.mySpringWeb.domain.pay;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PaymentVO {
	private String tid;
	private String user_id;
	private int amount;
	private String paytype;
	private Timestamp created_at;

	public String getCreated_at() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(created_at);
	}
	public String toString() {
		return String.format(
			"PaymentVO[\n" +
			"\ttid=%s\n" +
			"\tuser_id=%s\n" +
			"\tamount=%s\n" +
			"\tcreated_at=%s\n" +
			"\tpaytype=%s\n" +
			"]",
			tid, user_id, amount, created_at, paytype
		);
	}
}
