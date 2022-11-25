package gra.boundaries.output;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FindAwardedProducersOutputBoundary implements OutputBoundary {

	private final Set<AwardIntervalOutputBoundary> min;

	private final Set<AwardIntervalOutputBoundary> max;

}
