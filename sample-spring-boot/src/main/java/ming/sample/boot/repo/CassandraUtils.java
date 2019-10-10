package ming.sample.boot.repo;

import com.datastax.driver.core.PagingState;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.SimpleStatement;
import ming.sample.boot.model.ResultWrapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;

import java.util.List;
import java.util.Optional;

@Slf4j
public class CassandraUtils {
  public static final int DEFAULT_PAGE_SIZE = 20;

  public static <T> List<T> scanCassandraTable(final CassandraTemplate cassandraTemplate,
                                               Class<T> clazz) {
    final Integer limit = 2;

    final List<T> items = Lists.newArrayList();

    SimpleStatement statement = new SimpleStatement("select * from entities");
    statement.setFetchSize(limit);

    PagingState nextPageState = null;
    do {
      if (nextPageState != null) {
        statement.setPagingState(nextPageState);
      }
      final ResultSet resultSet = cassandraTemplate.getCqlOperations().queryForResultSet(statement);
      nextPageState = resultSet.getExecutionInfo().getPagingState();

      final MappingCassandraConverter converter = new MappingCassandraConverter();
      for (final Row row : resultSet) {
        try {
          final T entity = converter.readRow(clazz, row);
          items.add(entity);
        } catch (Exception e) {
          log.error("Error while reading row: {} {}", row.toString(), e);
        }
      }

    } while (nextPageState != null);

    return items;
  }

  public static <T> ResultWrapper<T> fetchCassandraPage(final CassandraTemplate cassandraTemplate,
                                                        Class<T> clazz,
                                                        String cql,
                                                        CassandraPageRequest pageRequest) {
    pageRequest = Optional.ofNullable(pageRequest).orElse(CassandraPageRequest.of(0, DEFAULT_PAGE_SIZE));
    final List<T> items = Lists.newArrayList();
    SimpleStatement statement = new SimpleStatement(cql);
    statement.setFetchSize(pageRequest.getPageSize());

    if (pageRequest.getPagingState() != null) {
      statement.setPagingState(pageRequest.getPagingState());
    }
    final ResultSet resultSet = cassandraTemplate.getCqlOperations().queryForResultSet(statement);

    final MappingCassandraConverter converter = new MappingCassandraConverter();
    for (final Row row : resultSet) {
      try {
        final T entity = converter.readRow(clazz, row);
        items.add(entity);
      } catch (Exception e) {
        log.error("Error while reading row: {} {}", row.toString(), e);
      }
    }

    ResultWrapper<T> result = new ResultWrapper<>();
    result.setNextPageState(resultSet.getExecutionInfo().getPagingState());
    result.setContent(items);
    return result;
  }

}
