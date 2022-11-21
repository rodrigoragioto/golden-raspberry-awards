package gra.h2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2ProducerRepository extends CrudRepository<H2Producer, Long> {

}
