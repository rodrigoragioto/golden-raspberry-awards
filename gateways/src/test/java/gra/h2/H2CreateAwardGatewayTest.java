package gra.h2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import gra.Award;

@DataJpaTest
@Import(H2GatewaysConfiguration.class)
class H2CreateAwardGatewayTest {

	@Autowired
	private H2CreateAwardGateway createAwardGateway;

	@Test
	void execute() {
		createAwardGateway.execute(Award.builder().build());
	}
}
