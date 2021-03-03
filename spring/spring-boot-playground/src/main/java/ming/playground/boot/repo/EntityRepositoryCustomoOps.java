package ming.playground.boot.repo;

import ming.playground.boot.model.Entity;

import java.util.List;
import java.util.function.Predicate;

/**
 * Use this interface to define some extra methods on the repository which may requires additional logic
 */
public interface EntityRepositoryCustomoOps {
  List<Entity> queryByPage(String cql, String pageState, int pageSize, Predicate<Entity> filter);
}
