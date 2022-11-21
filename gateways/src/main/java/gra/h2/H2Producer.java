package gra.h2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import gra.Award;
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

	public static H2Producer from(Producer producer) {
		return H2Producer.builder()
			.id(producer.getId())
			.name(producer.getName())
			.build();
	}

	@Setter(value = AccessLevel.PRIVATE)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	public Producer toProducer() {
		return Producer.builder()
			.id(this.getId())
			.name(this.getName())
			.build();
	}

}
