package com.shivamrajput.finance.hw.shivamrajputhw.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan("com.shivamrajput")
@EnableJpaRepositories("com.shivamrajput")
@EntityScan("com.shivamrajput")
public class ShivamrajputHwApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShivamrajputHwApplication.class, args);
	}


}
