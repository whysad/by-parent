package cn.hongtianren.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import cn.hongtianren.entity.Admin;

@CacheConfig(cacheNames = "baseCache")
@Mapper
public interface AdminMapper {

	Integer addAdmin(@Param("username") String username, @Param("password") String password);

	@Select("select * from admin")
	@Cacheable
	List<Admin> findList();

	Admin findAdmin(@Param("username") String username);

	/**
	 * 根据账号获取用户名
	 * 
	 * @param username
	 *            账号
	 * @return 用户名
	 */
	@Select("select name from admin where username = #{username}")
	String findNameByUsername(@Param("username") String username);

}
