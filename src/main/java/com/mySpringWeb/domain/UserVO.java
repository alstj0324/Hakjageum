package com.mySpringWeb.domain;

import java.sql.Date;

public class UserVO {
	private String id;
	private String email;
	private String pwd;
	private String nickname;
	private String provider;
	private int role_id;
	private Date create_at;

   public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	public String toString() {
	   return "UserVO [id="+id+",pwd="+pwd+"nickname="+nickname+",email="+email+",provider="+provider+",role_id=" + role_id + ",create_date="+create_at+"]";
   }
}