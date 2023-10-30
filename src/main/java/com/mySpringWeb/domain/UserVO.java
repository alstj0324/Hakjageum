package com.mySpringWeb.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
public class UserVO {
	private String id;
	private String email;
	private String pwd;
	private String nickname;
	private String provider;
	private int role_id;
	private Date create_at;
	private int point;

	public String toString() {
        return (
            String.format(
                "UserVO[\n" +
                "\tid=%s\n" +
                "\temail=%s\n" +
                "\tpwd=%s\n" +
                "\tnickname=%s\n" +
                "\tprovider=%s\n" +
                "\trole_id=%s\n" +
                "\tcreate_at=%s\n" +
                "\tpoint=%s\n" +
                "]",
                id, email, pwd, nickname, provider, role_id, create_at, point
            )
        );
	}
}