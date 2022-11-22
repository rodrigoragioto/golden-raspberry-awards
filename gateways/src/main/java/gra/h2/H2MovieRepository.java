package gra.h2;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2MovieRepository extends CrudRepository<H2Movie, Long> {

	List<H2Movie> findAll();

}
