package gra.h2.find;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gra.Award;
import gra.h2.H2Award;
import gra.h2.H2AwardRepository;
import gra.ports.FindAwardsGateway;

@Component
class H2FindAwardsGateway implements FindAwardsGateway {

	@Autowired
	private H2AwardRepository awardRepository;

	@Override
	public List<Award> execute() {
		return awardRepository.findAll()
			.stream()
			.map(H2Award::toAward)
			.collect(toList());
	}

}

