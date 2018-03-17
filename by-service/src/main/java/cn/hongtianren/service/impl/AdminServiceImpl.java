package cn.hongtianren.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import cn.hongtianren.dao.AdminMapper;
import cn.hongtianren.entity.Admin;
import cn.hongtianren.service.AdminService;


@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private CacheManager cacheManager;
	@Override
	public Integer addAdmin(String username, String password) {
		cacheManager.getCache("baseCache").clear();
		return adminMapper.addAdmin(username,password);
	}
	@Override
	public List<Admin> findList() {
		return adminMapper.findList();
	}
	@Override
	public String findNameByUsername(String username) {
		return adminMapper.findNameByUsername(username);
	}

}
