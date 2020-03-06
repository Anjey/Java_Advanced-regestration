package ua.lviv.lgs.admission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"ua.lviv.lgs"})
@EntityScan("ua.lviv.lgs.domain")
@EnableJpaRepositories("ua.lviv.lgs.dao")
public class AdmissionSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdmissionSystemApplication.class, args);
	}

}
