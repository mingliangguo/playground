package ming.playground.boot.repo;

import ming.playground.boot.model.Entity;
import org.springframework.data.repository.CrudRepository;

public interface EntityRepository extends CrudRepository<Entity, String>, EntityRepositoryCustomoOps {

}
