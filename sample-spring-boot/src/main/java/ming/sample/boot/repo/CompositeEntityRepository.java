package ming.sample.boot.repo;

import ming.sample.boot.model.CompositeEntity;
import ming.sample.boot.model.CompositeKey;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompositeEntityRepository extends CrudRepository<CompositeEntity, CompositeKey> {
  @Query("delete from composite_entities where first_id=?0")
  void deleteById(String firstId);
}
