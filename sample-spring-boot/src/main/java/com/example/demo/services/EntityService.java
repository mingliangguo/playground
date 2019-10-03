package com.example.demo.services;

import com.datastax.driver.core.PagingState;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.SimpleStatement;
import com.example.demo.model.Entity;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EntityService {
  @Autowired
  private CassandraTemplate cassandraTemplate;

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

  public List<Entity> readEntity() {
    List<Entity> entities = scanCassandraTable(cassandraTemplate, Entity.class);
    return entities;
  }
}
