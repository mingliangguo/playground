package ming.playground.boot.repo;

import com.datastax.driver.core.*;
import lombok.extern.slf4j.Slf4j;
import ming.playground.boot.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Slf4j
public class EntityRepositoryImpl implements EntityRepositoryCustomoOps {
  @Autowired
  Session session;
  @Autowired
  CassandraTemplate cassandraTemplate;

  @Override
  public List<Entity> queryByPage(String cql, String pageState, int pageSize, Predicate<Entity> filter) {
    List<Entity> entities = new ArrayList<>();
    SimpleStatement statement = new SimpleStatement(cql);
    final MappingCassandraConverter converter = new MappingCassandraConverter();

    ResultSet resultSet = null;
    PagingState pagingState = null;
    do {
      if (pagingState != null) {
        statement.setPagingState(pagingState);
      }
      resultSet = cassandraTemplate.getCqlOperations().queryForResultSet(statement);
      pagingState = resultSet.getExecutionInfo().getPagingState();
      for (final Row row : resultSet) {
        try {
          Entity entity = converter.readRow(Entity.class, row);
          // always return the entity if filter == null
          // otherwise test the value of the filter
          if (filter == null || filter.test(entity)) {
            entities.add(entity);
          }
        } catch (Exception e) {
          log.warn("can not convert the row, exception is: {}", e.getMessage());
          log.debug("exception of converting the row:", e);
        }
      }
    } while (pagingState != null && entities.size() < pageSize);

    return entities;
  }
}
