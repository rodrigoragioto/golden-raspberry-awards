package gra;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AwardedProducer {

	private final Producer producer;

	private final List<Integer> years;

}
