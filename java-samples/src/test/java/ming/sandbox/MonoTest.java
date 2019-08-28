/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ming.sandbox;

import org.junit.Assert;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

public class MonoTest {
    @Test public void testMonoWithoutThen() {
        Tuple2<Long, String> result = Mono.delay(Duration.ofMillis(3000))
          .map(d -> "Spring 4")
          .or(
            Mono.delay(Duration.ofMillis(2000))
            .map(d -> "spring 5")
          )
          .elapsed()
          .block();
      Assert.assertNotNull(result);
      Assert.assertTrue(result.getT1().intValue() >= 2000);
      Assert.assertTrue(result.getT1().intValue() <= 5000);
      Assert.assertEquals("spring 5", result.getT2());
    }
  @Test public void testMonoWithThen() {
    String s = Mono.delay(Duration.ofMillis(3000))
      .map(d -> "Spring 4")
      .or(
        Mono.delay(Duration.ofMillis(2000))
          .map(d -> "spring 5")
      )
      .elapsed()
      .log()
      .then(Mono.just("hello world"))
      .block();
    Assert.assertEquals("hello world", s);
  }
}
