package gra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import gra.h2.H2DataLoader;
import gra.h2.H2FindProducersGateway;
import gra.h2.H2MovieRepository;
import gra.h2.H2ProducerRepository;
import gra.ports.FindProducersGateway;

@SpringBootApplication
public class GoldenRaspberryAwardsApplication {

	final private Logger logger = LoggerFactory.getLogger(GoldenRaspberryAwardsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GoldenRaspberryAwardsApplication.class, args);
	}

	@Autowired
	private H2MovieRepository movieRepository;

	@Autowired
	private H2ProducerRepository producerRepository;

	@Bean
	DataLoader dataLoader() {
		return new H2DataLoader();
	}

	@Bean
	CommandLineRunner load(DataLoader dataLoader) {
		return args -> {
			if (args.length == 1) {
				try {
					dataLoader.execute(args[0]);
					logger.info("Data loaded successfully");
				} catch (Exception e) {
					logger.error("Error when loading data", e);
				}
			} else {
				logger.warn("No file provided");
			}
		};
	}

	@Bean
	FindProducersGateway findProducersGateway() {
		return new H2FindProducersGateway();
	}

	@Bean
	FindAwardsInterval findAwardsInterval() {
		return new FindAwardsIntervalImpl();
	}

}

