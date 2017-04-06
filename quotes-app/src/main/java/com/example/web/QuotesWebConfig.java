package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@Configuration
public class QuotesWebConfig {
	@Autowired
	Environment env;

	@Bean
	FilterRegistrationBean ng2FilterRegistrationBean() {
		Ng2Filter ng2Filter = new Ng2Filter("/");
		ng2Filter.setEnabled(Arrays.asList(env.getActiveProfiles()).contains("dev"));

		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(ng2Filter);
		return filterRegistrationBean;
	}
}
