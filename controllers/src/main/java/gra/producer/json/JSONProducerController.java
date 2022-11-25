package gra.producer.json;

import static gra.boundaries.input.EmptyInputBoundary.emptyInputBoundary;
import static gra.producer.json.JSONProducerController.ROOT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gra.FindAwardsInterval;
import gra.producer.AwardsIntervalPresenter;
import gra.producer.ProducerController;

@RestController
@RequestMapping(ROOT)
public class JSONProducerController implements ProducerController {

	static final String ROOT = "/api/v1/producer";

	static final String AWARDS_INTERVAL = "/awards-interval";

	@Autowired
	private FindAwardsInterval findAwardsInterval;

	@Override
	@GetMapping(AWARDS_INTERVAL)
	public AwardsIntervalPresenter awardsinterval() {
		return new JSONAwardsIntervalPresenter(findAwardsInterval.execute(emptyInputBoundary()));
	}

}
