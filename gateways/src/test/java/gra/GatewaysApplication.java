package gra;

import static java.util.Objects.requireNonNull;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import gra.h2.H2DataLoader;

@SpringBootApplication
public class GatewaysApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewaysApplication.class, args);
	}

	@Bean
	DataLoader dataLoader() {
		return new H2DataLoader();
	}

	@Bean
	ApplicationRunner load(DataLoader dataLoader) {
		final String path = requireNonNull(getClass().getClassLoader().getResource("movielist.csv")).getPath();
		return args -> dataLoader.execute(path);
	}

}
