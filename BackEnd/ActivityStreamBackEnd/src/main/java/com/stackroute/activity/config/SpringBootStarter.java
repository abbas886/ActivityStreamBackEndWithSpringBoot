package com.stackroute.activity.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
@ComponentScan(basePackages= {"com.stackroute.activity"})
@SpringBootApplication(scanBasePackages={"com.stackroute.activity"})
@EntityScan(basePackages={"com.stackroute.activity.model"})
@EnableAutoConfiguration
public class SpringBootStarter {
	
	@Bean
  	public HibernateJpaSessionFactoryBean sessionFactory() {
  	    return new HibernateJpaSessionFactoryBean();
  	}

	
}
