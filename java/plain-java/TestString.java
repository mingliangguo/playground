import java.util.*;

public class TestString {
  public static void main(String[] args) {
    final String preTag = "\ue000";
    final String postTag = "\ue001";
    System.out.println("preTag is:" + preTag);
    System.out.println("postTag is:" + postTag);
    TestString ts = new TestString();
    ts.test();
  }


  public static void test() {
    Scanner console = new Scanner(System.in);
    boolean isValid = false;
    int red = 0;
    while (!isValid) {
      try {
        red = console.nextInt();
        isValid = true;
        System.out.println("read a valid int:" + red);
      } catch(Exception e){
        System.out.println("Please enter an integer: ");
        console.next();
      }
    }
  }
  public static void test1(String filename) {
    Scanner scanner = null;
    boolean isValid = false;
    while (!isValid) {
      if (filename.endsWith(".ppm")) {
        scanner = new Scanner(new File(filename));
        return scanner;
      }

    }
  }

}
