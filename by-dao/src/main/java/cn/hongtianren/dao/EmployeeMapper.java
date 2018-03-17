package cn.hongtianren.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.hongtianren.entity.Employee;


/**
 * 
 * @Description 员工表dao
 * @author 谭震弘
 * @time 2017年12月31日
 * @version 1.0
 */
@Mapper
public interface EmployeeMapper {
	
	/**
	 * 根据账号获取员工id
	 * 
	 * @param username
	 *            账号
	 * @return 员工id
	 */
	@Select("select id from employee where phone = #{username}")
	Long findEmployeeId(@Param("username") String username);
	
	void saveEmployee(Employee employee);
	
	
	
}
