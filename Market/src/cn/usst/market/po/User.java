package cn.usst.market.po;

import java.util.Date;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String gender;
	private  String address;
	private Date birthday;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", gender="
				+ gender + ", address=" + address + ", birthday=" + birthday + "]";
	}
	public User(int id, String username, String gender, String address) {
		super();
		this.id = id;
		this.username = username;
		this.gender = gender;
		this.address = address;
	}
	public User(){
		
	}
	
	
	

}
