package voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import voting.model.Vote;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {
    @Query(value = "SELECT catssss FROM (SELECT cat.id as catssss, count(cat.id) FROM cat" +
            " left outer join vote ON cat.id = vote.cat_id GROUP BY cat.id ORDER BY" +
            " count(vote.cat_id) DESC)\n", nativeQuery = true)
    Iterable<Long> findTop10CatsIdOrderByDesc();
}
