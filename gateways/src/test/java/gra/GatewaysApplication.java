package gra;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import gra.h2.H2DataLoader;
import gra.h2.H2MovieRepository;
import gra.h2.H2ProducerRepository;

@SpringBootApplication
public class GatewaysApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewaysApplication.class, args);
	}

	@Bean
	ApplicationRunner dataLoader(H2ProducerRepository producerRepository, H2MovieRepository movieRepository) {
		return new H2DataLoader(producerRepository, movieRepository);
	}

}
