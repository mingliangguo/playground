import org.junit.*;
import static org.junit.Assert.*;

public class CarTest {
  @Test
  public void testRun() {
    System.out.println("car is running");
    fail("fail on purpose!");
  }
}
