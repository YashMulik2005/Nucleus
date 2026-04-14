package com.example.Nucleus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NucleusApplication {
	public static void main(String[] args) {
		SpringApplication.run(NucleusApplication.class, args);
	}

}
