package com.optica.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class OpticamanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpticamanagerApplication.class, args);
	}

}
