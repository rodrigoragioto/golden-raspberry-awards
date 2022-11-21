package gra.h2;

import static javax.persistence.CascadeType.ALL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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

	@JoinColumn(name = "PRODUCER_ID", nullable = false)
	@OneToOne(cascade = ALL, orphanRemoval = true)
	private H2Producer producer;

	public Movie toMovie() {
		return Movie.builder()
			.id(this.getId())
			.name(this.getName())
			.producer(this.getProducer().toProducer())
			.build();
	}

}
