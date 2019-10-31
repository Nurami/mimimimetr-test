package voting.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import voting.model.Cat;

import java.util.Optional;

@Repository
public interface CatRepository extends CrudRepository<Cat, Long> {
    Optional<Cat> findById(Long id);
    @Query(value = "SELECT * FROM cat ORDER BY RAND()", nativeQuery = true)
    Iterable<Cat> findAllByRand();
}
