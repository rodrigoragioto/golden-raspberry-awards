package gra;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AwardInterval {

	private final Producer producer;

	private final Integer previousWin;

	private final Integer followingWin;

	public final Integer interval;

	public AwardInterval(Producer producer, Integer previousWin, Integer followingWin, Integer interval) {
		this.producer = producer;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
		this.interval = interval;
	}

}
