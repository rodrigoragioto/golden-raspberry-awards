package gra.h2;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2AwardRepository extends CrudRepository<H2Award, Long> {

	List<H2Award> findAll();

}
