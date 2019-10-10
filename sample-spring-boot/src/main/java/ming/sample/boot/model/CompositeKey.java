package ming.sample.boot.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class CompositeKey {
  @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
  private String first_id;

  @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED)
  public String second_id;

  @PrimaryKeyColumn(ordinal = 2, type = PrimaryKeyType.CLUSTERED)
  private String third_id;
}
