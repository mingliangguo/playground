package ming.playground.boot.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table("composite_entities")
public class CompositeEntity {
  @PrimaryKey
  public CompositeKey key;
  @Column
  public String name;
}
