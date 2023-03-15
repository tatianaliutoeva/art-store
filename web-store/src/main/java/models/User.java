package models;

public class User {
	private int id;
	private String login;
	private String password;
	private String username;
	private String lastname;
	private String phone;
	public User(String login, String password, String username,String lastname,String phone) {
		this.login = login;
		this.password = password;
		this.username = username;
		this.lastname = lastname;
		this.phone = phone;
	}
	public int getId() {
		return id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}