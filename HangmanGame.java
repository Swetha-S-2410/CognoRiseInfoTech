import java.util.Random;
import java.util.Scanner;

public class HangmanGame {

    public static void main(String[] args) {
        String[] words = { "java", "hangman", "programming", "computer", "code" };

        // Select a random word
        Random random = new Random();
        String selectedWord = words[random.nextInt(words.length)];

        // Initialize variables
        int maxAttempts = 6;
        char[] wordToGuess = new char[selectedWord.length()];
        boolean[] guessedLetters = new boolean[26]; // to track guessed letters
        int incorrectGuesses = 0;

        // Initialize wordToGuess array with underscores
        for (int i = 0; i < wordToGuess.length; i++) {
            wordToGuess[i] = '_';
        }

        // Game loop
        while (incorrectGuesses < maxAttempts) {
            // Display the current state of the word
            displayWord(wordToGuess);

            // Prompt the user for a letter
            char guess = promptForLetter();

            // Check if the letter is in the word
            if (isLetterInWord(guess, selectedWord, wordToGuess, guessedLetters)) {
                System.out.println("Good guess!");
            } else {
                System.out.println("Incorrect guess!");
                incorrectGuesses++;
                displayHangman(incorrectGuesses);
            }

            // Check if the word is complete
            if (isWordComplete(wordToGuess)) {
                System.out.println("Congratulations! You guessed the word: " + selectedWord);
                break;
            }
        }

        // Display the final result
        if (incorrectGuesses == maxAttempts) {
            System.out.println("Sorry, you've run out of attempts. The correct word was: " + selectedWord);
        }
    }

    private static void displayWord(char[] wordToGuess) {
        System.out.print("Word: ");
        for (char letter : wordToGuess) {
            System.out.print(letter + " ");
        }
        System.out.println();
    }

    private static char promptForLetter() {
        Scanner scanner = new Scanner(System.in);
        char guess;
        while (true) {
            System.out.print("Enter a letter: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                guess = input.charAt(0);
                break;
            } else {
                System.out.println("Invalid input. Please enter a single letter.");
            }
        }

        return guess;
    }

    private static boolean isLetterInWord(char guess, String word, char[] wordToGuess, boolean[] guessedLetters) {
        boolean letterFound = false;

        // Check if the guessed letter is in the word
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess && !guessedLetters[guess - 'a']) {
                wordToGuess[i] = guess;
                letterFound = true;
            }
        }

        // Mark the guessed letter
        guessedLetters[guess - 'a'] = true;

        return letterFound;
    }

    private static boolean isWordComplete(char[] wordToGuess) {
        for (char letter : wordToGuess) {
            if (letter == '_') {
                return false;
            }
        }
        return true;
    }

    private static void displayHangman(int incorrectGuesses) {
        // Display the hangman figure based on incorrect guesses
        switch (incorrectGuesses) {
            case 1:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                System.out.println(" |");
                System.out.println(" |");
                break;
            case 2:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                System.out.println(" |    |");
                System.out.println(" |");
                break;
            case 3:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                System.out.println(" |   /|");
                System.out.println(" |");
                break;
            case 4:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                System.out.println(" |   /|\\");
                System.out.println(" |");
                break;
            case 5:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                System.out.println(" |   /|\\");
                System.out.println(" |   /");
                break;
            case 6:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                System.out.println(" |   /|\\");
                System.out.println(" |   / \\");
                break;
        }
    }
}
