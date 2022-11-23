package gra;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import gra.boundaries.input.EmptyInputBoundary;
import gra.boundaries.output.FindAwardedProducersOutputBoundary;
import gra.ports.FindProducersGateway;

@SpringBootTest
class FindAwardedProducersImplTest {

	@TestConfiguration
	static class FindAwardedProducersImplTestConfiguration {

		@MockBean
		private FindProducersGateway findProducersGateway;

		@Bean
		public FindAwardedProducers findAwardedProducers() {
			return new FindAwardedProducersImpl(findProducersGateway);
		}
	}

	@Autowired
	private FindAwardedProducers findAwardedProducers;

	@Test
	void shouldExecuteSuccessfully() {
		final FindAwardedProducersOutputBoundary boundary = findAwardedProducers.execute(EmptyInputBoundary.builder().build());
	}

}
