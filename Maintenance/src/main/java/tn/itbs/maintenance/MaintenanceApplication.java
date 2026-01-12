package tn.itbs.maintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableRabbit
@SpringBootApplication
public class MaintenanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaintenanceApplication.class, args);
	}

}
