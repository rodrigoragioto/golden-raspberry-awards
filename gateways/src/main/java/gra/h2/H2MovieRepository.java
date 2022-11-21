package gra.h2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2MovieRepository extends CrudRepository<H2Movie, Long> {

}
