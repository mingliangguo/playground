import java.util.*;
public class NumberGuess {
  private static void guess() {
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    int target = random.nextInt(101);
    int low = 0, high = 100;
    while (true) {
      System.out.format("guess a number between %d - %d: \n", low, high);
      int guess = scanner.nextInt();
      if (guess == target) {
        System.out.println("You got it! The number is: " + target);
        return;
      }
      if (guess < low || guess > high) {
        System.out.format("Your guess is out of range, input a number between %d and %d! \n", low, high);
        continue;
      }
      if (guess > target) {
        high = guess;
        System.out.println("Your guess is larger than the target");
      } else {
        low = guess;
        System.out.println("Your guess is smaller than the target");
      }
    }
  }
  public static void main(String[] args) {
    guess();
  }
}
