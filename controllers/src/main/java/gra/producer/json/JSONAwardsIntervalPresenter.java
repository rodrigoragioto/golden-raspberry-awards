package gra.producer.json;

import static java.util.stream.Collectors.toSet;

import java.util.Set;

import gra.boundaries.output.AwardsIntervalOutputBoundary;
import gra.producer.AwardsIntervalPresenter;
import lombok.Data;
import lombok.NonNull;

@Data
public class JSONAwardsIntervalPresenter implements AwardsIntervalPresenter {

	@NonNull
	private final Set<JSONAwardIntervalPresenter> min;

	@NonNull
	private final Set<JSONAwardIntervalPresenter> max;

	JSONAwardsIntervalPresenter(AwardsIntervalOutputBoundary boundary) {
		this.min = boundary.getMin().stream().map(JSONAwardIntervalPresenter::new).collect(toSet());
		this.max = boundary.getMax().stream().map(JSONAwardIntervalPresenter::new).collect(toSet());
	}

}
