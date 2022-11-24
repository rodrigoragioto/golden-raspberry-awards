package gra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AwardInterval {

	private final Producer producer;

	private final Integer previousWin;

	private final Integer followingWin;

	public Integer getInterval() {
		return followingWin - previousWin;
	}

}
