package gra.h2;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import gra.Movie;
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
@Entity(name = "MOVIE")
public class H2Movie {

	@Setter(value = AccessLevel.PRIVATE)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "MOVIE_YEAR", nullable = false)
	private Integer year;

	@Column(name = "WINNER", nullable = false)
	private boolean winner;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "MOVIE_PRODUCERS", joinColumns = @JoinColumn(name = "MOVIE_ID"), inverseJoinColumns = @JoinColumn(name = "PRODUCER_ID"))
	private List<H2Producer> producers;

	public Movie toMovie() {
		return Movie.builder()
			.id(this.getId())
			.name(this.getName())
			.year(this.getYear())
			.winner(this.isWinner())
			.producers(this.getProducers()
				.stream()
				.map(H2Producer::toProducer)
				.collect(toList()))
			.build();
	}

}
