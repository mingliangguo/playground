package ming.playground.boot.services;

import com.datastax.driver.core.PagingState;
import ming.playground.boot.model.Entity;
import ming.playground.boot.model.ResultWrapper;
import ming.playground.boot.repo.CassandraUtils;
import lombok.extern.slf4j.Slf4j;
import ming.playground.boot.repo.CompositeEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EntityService {
  @Autowired
  private CassandraTemplate cassandraTemplate;

  @Autowired
  private CompositeEntityRepository compositeEntityRepository;

  public List<Entity> readEntity() {
    PagingState state;
    List<Entity> result = new ArrayList<>();

    do {
      ResultWrapper<Entity> entities = CassandraUtils.fetchCassandraPage(cassandraTemplate, Entity.class,
        "select * from entities", CassandraPageRequest.of(0, 2));
      state = entities.getNextPageState();
      result.addAll(entities.getContent());
    }  while (state != null);
    return result;
  }

  public void deleteCompositeEntityByFirstId(String firstId) {
    compositeEntityRepository.deleteById(firstId);
  }
}
