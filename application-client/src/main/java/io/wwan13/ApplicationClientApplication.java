package io.wwan13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "io.wwan13")
public class ApplicationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationClientApplication.class, args);
	}

}
