package ming.sample.boot.repo;

import ming.sample.boot.model.Entity;
import org.springframework.data.repository.CrudRepository;

public interface EntityRepository extends CrudRepository<Entity, String> {

}
