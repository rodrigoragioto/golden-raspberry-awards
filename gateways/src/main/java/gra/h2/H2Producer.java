package gra.h2;

import static java.util.stream.Collectors.toSet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import gra.Producer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PRODUCER")
public class H2Producer {

	@Setter(value = AccessLevel.PRIVATE)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	@ManyToMany
	@JoinTable(
		name = "PRODUCER_MOVIES",
		joinColumns = @JoinColumn(name = "PRODUCER_ID"),
		inverseJoinColumns = @JoinColumn(name = "MOVIE_ID"),
		uniqueConstraints = {@UniqueConstraint(columnNames = {"PRODUCER_ID", "MOVIE_ID"})})
	private Set<H2Movie> movies;

	public Producer toProducer() {
		return Producer.builder()
			.id(this.getId())
			.name(this.getName())
			.movies(this.getMovies()
				.stream()
				.map(H2Movie::toMovie)
				.collect(toSet()))
			.build();
	}

	public void addMovie(H2Movie h2Movie) {
		movies.add(h2Movie);
	}

}
