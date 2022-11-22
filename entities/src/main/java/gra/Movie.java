package gra;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Movie {

	private final Long id;

	private final String name;

	private final Integer year;

	private final boolean winner;

	private final List<Producer> producers;

}
