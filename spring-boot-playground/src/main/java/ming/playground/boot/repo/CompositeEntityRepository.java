package ming.playground.boot.repo;

import ming.playground.boot.model.CompositeEntity;
import ming.playground.boot.model.CompositeKey;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompositeEntityRepository extends CrudRepository<CompositeEntity, CompositeKey> {
  @Query("delete from composite_entities where first_id=?0")
  void deleteById(String firstId);
}
