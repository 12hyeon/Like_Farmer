package likelion.Spring_Like_Farmer;

import likelion.Spring_Like_Farmer.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication
public class SpringLikeFarmerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLikeFarmerApplication.class, args);
	}

}
