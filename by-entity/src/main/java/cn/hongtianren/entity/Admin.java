package cn.hongtianren.entity;

import java.util.Set;

/**
 * 
 * @Description 用户实体类
 * @author 谭震弘
 * @time 2017年12月30日
 * @version 1.0
 */
public class Admin extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4127008977469725649L;
	/** 账号 */
	private String username;
	/** 密码 */
	private String password;
	/** 用户名 */
	private String name;
	/** 角色 */
	private Set<String> roles;
	/** 权限 */
	private Set<String> permissions;

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

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
