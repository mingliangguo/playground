import java.util.*;

public class FormatString {
  public static void main(String[] args) {
    List<String[]> lists = Arrays.asList(
        new String[]{ "00003", "2021-05-01", "2021-07-31", "100", "Office:","4-20" },
        new String[]{ "00003", "2021-05-01", "2021-07-31", "100", "Office:", "4-20" },
        new String[]{ "00003", "2021-05-01", "2021-07-31", "100", "Conference Room:", "13-27" },
        new String[]{ "00003", "2021-05-01", "2021-07-31", "100", "Hotel Suite:", "11-34" }
        );

    for (String[] arr: lists ) {
      String result = String.format("%6s | %10s to %10s | %3s | %-20s %s", arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
      System.out.println(result);
    }
  }
}
