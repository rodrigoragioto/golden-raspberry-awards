package gra.h2;

import org.springframework.stereotype.Component;

import gra.Award;
import gra.h2.H2Award;
import gra.h2.H2AwardRepository;
import gra.ports.CreateAwardGateway;

@Component
class H2CreateAwardGateway implements CreateAwardGateway {

	H2CreateAwardGateway(H2AwardRepository awardRepository) {
		this.awardRepository = awardRepository;
	}

	private final H2AwardRepository awardRepository;

	@Override
	public Award execute(Award award) {
		return awardRepository.save(H2Award.from(award)).toAward();
	}
}

