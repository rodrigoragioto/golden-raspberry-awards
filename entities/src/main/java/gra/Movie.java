package gra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Movie {

	private final Long id;

	private final String name;

	private final Producer producer;

}
