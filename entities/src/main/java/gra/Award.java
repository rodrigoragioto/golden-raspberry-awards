package gra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Award {

	private final Long id;

	private final Integer year;

	private final Movie winner;

}
