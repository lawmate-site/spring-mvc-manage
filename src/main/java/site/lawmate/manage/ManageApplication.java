package site.lawmate.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ManageApplication{

	public static void main(String[] args) {
		SpringApplication.run(ManageApplication.class, args);
	}

}
