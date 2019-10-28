import java.util.*;
import java.io.*;

public class ScannerTest {
  public static void main(String... args) {
    ScannerTest.readFileByLine("test.txt");
  }
  public static void readFileByLine(String fileName) {
    try {
      File file = new File(fileName);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        System.out.println("line is: " + line);
        Scanner lineScanner = new Scanner(line);
        lineScanner.useDelimiter(" ");

        System.out.println("name is: " + lineScanner.next());
        System.out.println("Integer is: " + lineScanner.nextInt());
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
