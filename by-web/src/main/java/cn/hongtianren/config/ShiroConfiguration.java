package cn.hongtianren.config;

import java.util.LinkedHashMap;


import java.util.Map;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import cn.hongtianren.realm.MyShiroRealm;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;


@Configuration
public class ShiroConfiguration {
	 private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

	    /**
	     * Shiro的Web过滤器Factory 命名:shiroFilter<br /> * * @param securityManager * @return
	     */
	    @Bean(name = "shiroFilter")
	    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
	        logger.info("注入Shiro的Web过滤器-->shiroFilter", ShiroFilterFactoryBean.class);
	        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();	

	        //Shiro的核心安全接口,这个属性是必须的
	        shiroFilterFactoryBean.setSecurityManager(securityManager);
	        //要求登录时的链接(可根据项目的URL进行替换),非必须的属性,默认会自动寻找Web工程根目录下的"/login.jsp"页面
	        shiroFilterFactoryBean.setLoginUrl("/login");
	        //登录成功后要跳转的连接,逻辑也可以自定义，例如返回上次请求的页面
	        shiroFilterFactoryBean.setSuccessUrl("/index");
	        //用户访问未对其授权的资源时,所显示的连接
	        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

	        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
	        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
	        filterChainDefinitionMap.put("/logout", "logout");

	        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
	        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
	        filterChainDefinitionMap.put("/css/**", "anon");
	        filterChainDefinitionMap.put("/druid/**", "anon");
	        filterChainDefinitionMap.put("/images/**", "anon");
	        filterChainDefinitionMap.put("/js/**", "anon");
	        filterChainDefinitionMap.put("/font-awesome/**", "anon");
	        filterChainDefinitionMap.put("/easyui/**", "anon");
	        filterChainDefinitionMap.put("/login", "anon");//anon 可以理解为不拦截
	        filterChainDefinitionMap.put("/**", "user");

	        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

	        return shiroFilterFactoryBean;
	    }
	    
	    @Bean
	    public EhCacheManager ehCacheManager() {
	        System.out.println("ShiroConfiguration.getEhCacheManager()");
	        EhCacheManager ehCacheManager = new EhCacheManager();
	        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
	        return ehCacheManager;
	    }

	    /**
	     * 不指定名字的话，自动创建一个方法名第一个字母小写的bean * @Bean(name = "securityManager") * @return
	     */
	    @Bean
	    public SecurityManager securityManager(MyShiroRealm myShiroRealm) {
	        logger.info("注入Shiro的Web过滤器-->securityManager", ShiroFilterFactoryBean.class);
	        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
	        securityManager.setRealm(myShiroRealm);
	        securityManager.setCacheManager(ehCacheManager());
	        //securityManager.setRememberMeManager(cookieRememberMeManager());
	        return securityManager;
	    }
	    
//	  //cookie对象;
//	    @Bean
//	    public SimpleCookie rememberMeCookie() {
//	        System.out.println("ShiroConfiguration.rememberMeCookie()");
//	        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
//	        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//
//	        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
//	        simpleCookie.setMaxAge(259200);
//	        return simpleCookie;
//	    }
//
//	    //cookie管理对象;
//	    @Bean
//	    public CookieRememberMeManager cookieRememberMeManager() {
//	        System.out.println("ShiroConfiguration.rememberMeManager()");
//	        CookieRememberMeManager manager = new CookieRememberMeManager();
//	        manager.setCookie(rememberMeCookie());
//	        return manager;
//	    }
//	    
	    /**
	     * Shiro生命周期处理器 * @return
	     */
	    @Bean
	    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
	        return new LifecycleBeanPostProcessor();
	    }

	    /**
	     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能 * @return
	     */
	    @Bean
	    @DependsOn({"lifecycleBeanPostProcessor"})
	    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
	        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
	        advisorAutoProxyCreator.setProxyTargetClass(true);
	        return advisorAutoProxyCreator;
	    }

	    @Bean
	    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
	        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
	        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
	        return authorizationAttributeSourceAdvisor;
	    }
}
