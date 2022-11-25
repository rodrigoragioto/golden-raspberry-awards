package gra.producer.json;

import gra.boundaries.output.AwardIntervalOutputBoundary;
import gra.producer.AwardsIntervalPresenter;
import lombok.Data;

@Data
public class JSONAwardIntervalPresenter implements AwardsIntervalPresenter {

	private final String producer;

	public final Integer interval;

	private final Integer previousWin;

	private final Integer followingWin;

	JSONAwardIntervalPresenter(AwardIntervalOutputBoundary boundary) {
		this.producer = boundary.getProducer();
		this.interval = boundary.getInterval();
		this.previousWin = boundary.getPreviousWin();
		this.followingWin = boundary.getFollowingWin();
	}

}
