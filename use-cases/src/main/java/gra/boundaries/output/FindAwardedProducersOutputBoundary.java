package gra.boundaries.output;

import java.util.Set;

import gra.AwardedProducer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FindAwardedProducersOutputBoundary implements OutputBoundary {

	private final Set<AwardedProducer> min;

	private final Set<AwardedProducer> max;

}
