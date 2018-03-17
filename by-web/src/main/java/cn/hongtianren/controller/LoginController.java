package cn.hongtianren.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.hongtianren.entity.Admin;
import cn.hongtianren.service.AdminService;

@Controller
public class LoginController {

	@Autowired
	private AdminService adminService;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@PostMapping("/login")
	public String login(Admin admin, RedirectAttributes attributes) {
		if (admin.getUsername() == null || admin.getUsername() == null) {
			return "login";
		}
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(admin.getUsername(),
				admin.getPassword());
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(usernamePasswordToken);
		} catch (Exception e) {
			logger.warn("用户不存在" + admin.getUsername());
			attributes.addFlashAttribute("message", "用户名或密码不正确");
		}
		if (subject.isAuthenticated()) {
			return "redirect:/index";
		} else {
			usernamePasswordToken.clear();
			return "redirect:/login";
		}
	}

	@GetMapping("/logout")
	public String logout(RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
		return "redirect:/login";
	}

	@GetMapping("/index")
	public String index(HttpSession session) {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		session.setAttribute("name", adminService.findNameByUsername(username));
		return "index";
	}

	/**
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping("/desktop")
	public String desktop() {
		return "desktop";
	}
}
