import java.util.*;
import java.io.*;

public class ImageEditor {

    public static void main (String[] args) throws FileNotFoundException{
      String line = "12 222 25";
      System.out.println(ImageEditor.convertLineToHighContrast(line));
    }
    public static void main1 (String[] args) throws FileNotFoundException{

        String filename = "";
        int red = 0;
        int green = 0;
        int blue = 0;
        Scanner input = new Scanner(System.in);
        Scanner console = new Scanner(System.in);

        System.out.println("DISPLAY HEADER AS DESCRIBED ABOVE! ");

        getInputScanner(filename);
        PrintStream output = new PrintStream((getOutputPrintStream(console)));

        System.out.print("Enter H-high Contrast, G-rey Scale, or C-hange Intensity: ");
        String kind = console.next();
        char firstLetter = kind.charAt(0);

        if (firstLetter == 'H' || firstLetter == 'h') {
            highContrast(console, output);
        }
        if (firstLetter == 'G' || firstLetter == 'g') {
            greyScale(console,output);
        }
        if (firstLetter == 'C' || firstLetter == 'c') {
            System.out.println("Red change amount: ");
            red = console.nextInt();
            System.out.println("Green change amount: ");
            green = console.nextInt();
            System.out.println("Blue change amount: ");
            blue = console.nextInt();
            changeIntensity(input, output, red, green, blue);
        }
    }

    /**
     * If the filename does not end with a .ppm extension, output "Invalid filename" and return null
     * If the file does not exist, outputs "File does not exist" and returns null
     * This method must use try/catch to handle any exception
     * @param filename means the name of the file
     * @return Scanner for a input file
     */
    public static Scanner getInputScanner(String filename) {

        try {
            Scanner console = new Scanner(System.in);
            System.out.print("Enter input filename or Q to quit: ");
            filename = console.next();
            char name = filename.charAt(0);

            if (name =='q' || name == 'Q'){
                System.exit(1);
            }
            Scanner scanner = new Scanner(new File(filename));

            if (filename.endsWith(".ppm")) {
                return scanner;

            } else {
                System.out.println("Invalid filename");
                return null;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist. ");
            return null;
        }
    }

    /**
     * Prompts user to enter output filename and returns PrintStream for the file
     * If the filename does not end with a .ppm extension, output "Invalid filename" and returns null
     * If the file already exists, ask the user if it is OK to overwrite the file.
     * If the user's answer begins with y or Y, creates and returns a PrintStream for the file
     * Return null if it is not OK to overwrite the file
     * If a FileNotFoundException occurs, outputs "Cannot create output file" and returns null
     * This method must use try/catch to handle any exception
     * @param console shows the name that user type in
     * @return the file that is overwrited
     */
    public static PrintStream getOutputPrintStream(Scanner console) {

        String outputFile = "";
        File file = new File(outputFile);

        try{
            System.out.print("Enter filename for output: ");

            outputFile = console.next();

            PrintStream output = new PrintStream(new File(outputFile));

            if (outputFile.endsWith(".ppm")) {

                if (file.exists()) {
                    System.out.print("OK to overwrite file? (y/n): ");
                    char decision = console.next().charAt(0);

                    if (decision == 'y' || decision == 'Y') {
                        output.println("New output file was created.");
                        System.out.println(outputFile);

                    } else {
                        return null;
                    }
                }

            } else {
                System.out.println("Invalid filename");
                return null;
            }
            return output;
        } catch(FileNotFoundException e){
            System.out.println("Cannot create output file ");
            return null;
        }
    }

    /**
     * This method copies the input ppm file to the output ppm file, converting it to high contrast
     * @param input the input ppm file
     * @param output the output ppm file
     */
    public static void highContrast(Scanner input, PrintStream output) {

        while(input.hasNextLine()){
            String line = input.nextLine();
            String newLine = convertLineToHighContrast(line);
            output.println(newLine);
        }
    }

    /**
     * This method creates and returns a high contrast version of line
     * @param line shows the line that will be contrast
     * @return a high contrast version of line with one space between each color value
     * but no space at the end of the new line
     */
    public static String convertLineToHighContrast(String line) {

        Scanner lineScanner = new Scanner(line);
        System.out.println("line is:" + line);

        String contrastValue = "";
        while(lineScanner.hasNextInt()) {

            // System.out.println(lineScanner);

            int num = lineScanner.nextInt();

            if (num < 128) {
                contrastValue += 0;
            } else {
                contrastValue += 255;
            }
            contrastValue += "  ";
        }
        return contrastValue;
    }

    /**
     * Copies the input ppm file to the output ppm file, converting it to grey scale
     * @param input the input ppm file
     * @param output the output ppm file
     */
    public static void greyScale(Scanner input, PrintStream output) {

        while(input.hasNextLine()) {
            String line = input.nextLine();
            String newLine = convertLineToGreyScale(line);
            output.println(newLine);
        }
    }

    /**
     * Creates and returns a grey scale version of line
     * @param line shows the line that will be turned into grey
     * @return a grey scale version of line, with one space between each color value
     * but no space at the end of the new line
     */
    public static String convertLineToGreyScale(String line) {

        int averageValue = 0;
        String averageValue2= averageValue + "";
        Scanner lineScanner = new Scanner(line);

        while(lineScanner.hasNext()) {
            int greyValue = Integer.parseInt(lineScanner.next());

            for (int j = 0; j < 6; j++) {
                char b = line.charAt(j);
                if (b == ' ') {
                    greyValue += 0;
                } else {
                    greyValue += b;
                }
                averageValue = greyValue / 3;
            }
        }
        lineScanner.close();
        return averageValue2;
    }

    /**
     * Copies the input ppm file to the output ppm file
     * changing the intensity of each color by the given amount
     * @param input the input ppm file
     * @param output the output ppm file
     * @param redChange values that red will change
     * @param greenChange values that green will change
     * @param blueChange values that blue will change
     */
    public static void changeIntensity(Scanner input, PrintStream output, int redChange, int greenChange, int blueChange) {

        while(input.hasNextLine()) {
            String line = input.nextLine();
            String newLine = changeLineIntensity(line, redChange, greenChange, blueChange);
            output.println(newLine);
        }
    }

    /**
     * Creates and returns a copy of line changeing the intensity of each color
     * @param line the line that will change intensity
     * @param redChange values that red will change
     * @param greenChange values that green will change
     * @param blueChange values that blue will change
     * @return a copy of line changing the intensity of each color and with one space between each
     * color value, but no space at the end of the new line
     */
    public static String changeLineIntensity(String line, int redChange, int greenChange, int blueChange) {

        Scanner lineScanner = new Scanner(line);
        int densityValue = lineScanner.nextInt();
        String densityValue2 = densityValue + "";
        while(lineScanner.hasNext()) {

            for (int k = 0; k < line.length(); k++) {

                for (int l = 0; l < line.length(); l+=6) {
                    char c = line.charAt(l);
                    if (c == ' ') {
                        densityValue += ' ';
                    } else if (c < 0) {
                        redChange = 0;
                        densityValue += redChange;
                    } else if (c > 255) {
                        redChange = 255;
                        densityValue += redChange;
                    }
                }

                for (int m = 2; m < line.length(); m+=6) {
                    char d = line.charAt(m);
                    if (d == ' ') {
                        densityValue += ' ';
                    } else if (d < 0) {
                        greenChange = 0;
                        densityValue += greenChange;
                    } else if (d > 255) {
                        greenChange = 255;
                        densityValue += greenChange;
                    }
                }

                for(int n = 4; n < line.length(); n+=6) {
                    char e = line.charAt(n);
                    if (e == ' ') {
                        densityValue += ' ';
                    } else if (e < 0) {
                        blueChange = 0;
                        densityValue += blueChange;
                    } else if (e > 255) {
                        blueChange = 255;
                        densityValue += blueChange;
                    }
                }
            }
        }
        lineScanner.close();
        return densityValue2;
    }
}
