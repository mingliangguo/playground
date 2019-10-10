package ming.sample.boot.model;

import com.datastax.driver.core.PagingState;
import lombok.Data;

import java.util.List;

@Data
public class ResultWrapper<T> {
  List<T> content;
  PagingState nextPageState;
}
