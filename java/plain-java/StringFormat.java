import java.util.*;

public class StringFormat {
  public static void main(String[] args) {
    final String name = "Tom";
    final int num = 4;
    final boolean success = true;
    String s = String.format("%s %d %b", name, num, success);
    System.out.println("formatted string is:" + s);
  }
}
