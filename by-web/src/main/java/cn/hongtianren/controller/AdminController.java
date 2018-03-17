package cn.hongtianren.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hongtianren.entity.Admin;
import cn.hongtianren.service.AdminService;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<Admin> findList() {
		return adminService.findList();
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		map.put("name", "admin admin");
		adminService.addAdmin("tanzhenhong", "tanzhenhong");
		return "index";
	}

	@ResponseBody
	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public String log(String name) {
		return name;
	}
}
