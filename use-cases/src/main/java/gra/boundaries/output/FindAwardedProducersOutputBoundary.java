package gra.boundaries.output;

import java.util.Set;

import gra.AwardInterval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FindAwardedProducersOutputBoundary implements OutputBoundary {

	private final Set<AwardInterval> min;

	private final Set<AwardInterval> max;

}
