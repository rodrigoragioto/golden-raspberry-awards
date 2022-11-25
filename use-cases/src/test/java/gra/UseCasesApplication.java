package gra;

import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import gra.ports.FindProducersGateway;

@SpringBootApplication
public class UseCasesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UseCasesApplication.class, args);
	}

	@Bean
	FindProducersGateway findProducersGateway() {
		return Mockito.mock(FindProducersGateway.class);
	}

}
