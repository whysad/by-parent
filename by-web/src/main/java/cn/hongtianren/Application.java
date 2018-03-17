package cn.hongtianren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // 开启缓存
@EnableAutoConfiguration
@ServletComponentScan // 注册durid监控servlet
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
