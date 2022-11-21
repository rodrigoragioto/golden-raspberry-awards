package gra.h2;

import static javax.persistence.CascadeType.ALL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import gra.Award;
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
@Entity(name = "AWARD")
public class H2Award {

	@Setter(value = AccessLevel.PRIVATE)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "AWARD_YEAR", nullable = false)
	private Integer year;

	@JoinColumn(name = "MOVIE_ID", nullable = false)
	@OneToOne(cascade = ALL, orphanRemoval = true)
	private H2Movie winner;

	public Award toAward() {
		return Award.builder()
			.id(this.getId())
			.year(this.getYear())
			.winner(this.getWinner().toMovie())
			.build();
	}

}
