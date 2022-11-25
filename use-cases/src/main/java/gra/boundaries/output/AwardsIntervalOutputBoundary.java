package gra.boundaries.output;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
public class AwardsIntervalOutputBoundary implements OutputBoundary {

	@NonNull
	private final Set<AwardIntervalOutputBoundary> min;

	@NonNull
	private final Set<AwardIntervalOutputBoundary> max;

}
