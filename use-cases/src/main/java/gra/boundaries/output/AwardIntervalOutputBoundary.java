package gra.boundaries.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AwardIntervalOutputBoundary implements OutputBoundary {

	private final String producer;

	private final Integer previousWin;

	private final Integer followingWin;

	public final Integer interval;

}
