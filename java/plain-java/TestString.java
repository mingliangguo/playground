import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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
  public static void testToBytes() {
// write out the decimal with the precision and scale.
    NumberFormat numberFormat = DecimalFormat.getInstance();
    numberFormat.setGroupingUsed(false);
    normalizeNumberFormat(numberFormat, scale, precision);
    final String rawValue  = new String(((ByteBuffer)fieldValue).array());
    out.write(numberFormat.format(new BigDecimal(rawValue)).getBytes(StandardCharsets.UTF_8));
  }
    /**
     * According to the 1.7.7 spec If a logical type is invalid, for example a
     * decimal with scale greater than its precision,then implementations should
     * ignore the logical type and use the underlying Avro type.
     */
    private static void normalizeNumberFormat(NumberFormat numberFormat, int scale, int precision) {
        if (scale < precision) {
            // write out with the specified precision and scale.
            numberFormat.setMaximumIntegerDigits(precision);
            numberFormat.setMaximumFractionDigits(scale);
            numberFormat.setMinimumFractionDigits(scale);
        }
    }

}
