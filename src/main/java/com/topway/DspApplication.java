package com.topway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DspApplication {

	public static void main(String[] args) {
		SpringApplication.run(DspApplication.class, args);
	}

	/**
	 * request过滤器,校验token是否正确
	 * @return
	 */
//	@Bean
//	public FilterRegistrationBean jwtFilter() {
//		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
//		registrationBean.setFilter(filter);
//		return registrationBean;
//	}
}
