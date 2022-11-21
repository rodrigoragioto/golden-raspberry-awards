package gra;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GoldenRaspberryAwardsApplicationTests {

	@Test
	void contextLoads() {
		assertThat(true, is(true));
	}

}

