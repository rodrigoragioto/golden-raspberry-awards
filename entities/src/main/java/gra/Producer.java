package gra;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Producer {

	private final Long id;

	private final String name;

	private final Set<Movie> movies;

}
