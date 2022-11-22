package gra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AwardedProducerInterval {

	private final Producer producer;

	private final Integer interval;

	private final Integer previousWin;

	private final Integer followingWin;

}
