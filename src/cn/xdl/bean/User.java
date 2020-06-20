package cn.xdl.bean;

import java.io.Serializable;

/**
 * 与数据库中的用户表格的  格式 保持一致
 * @author lwj
 *
 */
public class User implements Serializable{

	private int id;
	private String username;
	private String password;
	private int isManager;
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
	public int getIsManager() {
		return isManager;
	}
	public void setIsManager(int isManager) {
		this.isManager = isManager;
	}
	
	
	public User(int id, String username, String password, int isManager) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.isManager = isManager;
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", isManager=" + isManager
				+ "]";
	}
	
	
}
