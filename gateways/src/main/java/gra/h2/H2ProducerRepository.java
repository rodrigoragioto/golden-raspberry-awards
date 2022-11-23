package gra.h2;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2ProducerRepository extends CrudRepository<H2Producer, Long> {

	Optional<H2Producer> findByName(String name);

	Set<H2Producer> findAll();

}
