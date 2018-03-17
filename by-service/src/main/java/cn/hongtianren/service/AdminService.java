package cn.hongtianren.service;

import java.util.List;

import cn.hongtianren.entity.Admin;



public interface AdminService {
	
	Integer addAdmin(String username,String password);

	List<Admin> findList();
	
	/**
	 * 根据账号获取用户名
	 * @param username 账号
	 * @return 用户名
	 */
	String findNameByUsername(String username);
}
