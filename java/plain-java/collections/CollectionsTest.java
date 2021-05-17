import java.util.*;
import java.util.stream.*;

public class CollectionsTest {
  public static void main(String[] args) {
    IntStream.range(0, 10).forEach(i -> {
      if (i % 2 == 0) {
        System.out.println("hello " + i);
      } else {
        throw new RuntimeException("Bad luck1");
      }
    });
  }
}
