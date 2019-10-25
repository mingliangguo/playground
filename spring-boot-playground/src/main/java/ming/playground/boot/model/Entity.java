package ming.playground.boot.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("entity")
public class Entity {
  @PrimaryKey
  public String id;
  @Column
  public String name;
}
