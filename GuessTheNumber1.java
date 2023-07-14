import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber1 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Random rand = new Random();

    int min = 1;
    int max = 100;
    int guessNumber = rand.nextInt(max - min + 1) + min;
    int userGuess;
    int tries = 0;
    boolean hasWon = false;
    System.out.println("Welcome to the Guess the Number game!");
    System.out.println("I'm thinking of a number between " + min + " and " + max + ".");
    System.out.println("You have 10 tries to guess the number.");

    while(tries < 10 && !hasWon) {
      System.out.println("Enter your guess: ");
      userGuess = input.nextInt();
      tries++;

      if(userGuess == guessNumber) {
        hasWon = true;
        System.out.println("Congratulations! You guessed the number in " + tries + " tries!");
      } else if(userGuess < guessNumber) {
        System.out.println("Your guess is too low. Try again.");
      } else {
        System.out.println("Your guess is too high. Try again.");
      }
    }

    if(!hasWon) {
      System.out.println("Sorry, you ran out of tries. The number was " + guessNumber + ".");
    }

    input.close();
  }
}



