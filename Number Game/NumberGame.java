import java.io.*;
import java.util.*;

public class NumberGame {

	private static final String DATA_FILE = "userdata.txt";
	private static Map<Integer, UserData> userDataMap = new HashMap<>();
	private static int currentUserID = 1;
	private static int loginUserID = -1;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Load user data from the file if it exists, create the file otherwise.
		loadUserData();

		System.out.println("Welcome to the Advanced Number Game!");

		while (true) {

			System.out.println("Options:");
			System.out.println("1. Login");
			System.out.println("2. View Scoreboard");
			System.out.println("3. Add New User");
			System.out.println("4. Forgot User ID");
			System.out.println("5. Play Game");
			System.out.println("6. How to Play");
			System.out.println("7. Exit");

			int choice = getChoice(scanner, 1, 7);

			if (choice == 1) {
				login(scanner);
			} else if (choice == 2) {
				viewScoreboard(loginUserID);
			} else if (choice == 3) {
				addNewUser(scanner);
			} else if (choice == 4) {
				forgotUserID(scanner);
			} else if (choice == 5) {
				if (loginUserID != -1) {
					playGame(scanner);
				} else {
					System.out.println("Please log in to play the game.");
				}
			} else if (choice == 6) {
				showInstructions(); // Call the "How to Play" function
			} else if (choice == 7) {
				saveUserData();
				break;
			}

		}

		System.out.println("\nGame Over! Thank you for playing.");
	}

	private static void loadUserData() {
		try (Scanner scanner = new Scanner(new File(DATA_FILE))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] userData = line.split(",");
				if (userData.length == 6) {
					int userID = Integer.parseInt(userData[0]);
					String name = userData[1];
					String email = userData[2];
					int highScore = Integer.parseInt(userData[3]);
					int totalScore = Integer.parseInt(userData[4]);
					int gamesPlayed = Integer.parseInt(userData[5]);
					userDataMap.put(userID, new UserData(userID, name, email, highScore, totalScore, gamesPlayed));
					// Update currentUserID if necessary
					if (userID >= currentUserID) {
						currentUserID = userID + 1;
					}
				}
			}
		} catch (FileNotFoundException e) {
			
		}
	}

	private static void saveUserData() {
		try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
			for (UserData userData : userDataMap.values()) {
				writer.println(userData.getUserID() + "," + userData.getName() + "," + userData.getEmail() + ","
						+ userData.getHighScore() + "," + userData.getTotalScore() + "," + userData.getGamesPlayed());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void login(Scanner scanner) {
		System.out.print("Enter your User ID: ");
		int userID = getChoice(scanner, 1, Integer.MAX_VALUE);

		System.out.print("Enter your username: ");
		String username = scanner.next();

		// Check if the user exists by User ID and username
		Optional<UserData> foundUser = getUserByIDAndName(userID, username);

		if (foundUser.isPresent()) {
			loginUserID = userID; // Set the loginUserID when a user successfully logs in
			String name = foundUser.get().getName();
			System.out.println("Logged in as " + name + " (User ID: " + userID + ").");
		} else {
			System.out.println("User not found. Please register as a new user.");
		}
	}

	private static Optional<UserData> getUserByIDAndName(int userID, String username) {
		for (UserData userData : userDataMap.values()) {
			if (userData.getUserID() == userID && userData.getName().equalsIgnoreCase(username)) {
				return Optional.of(userData);
			}
		}
		return Optional.empty();
	}

	private static void forgotUserID(Scanner scanner) {
		System.out.print("Enter your name: ");
		String name = scanner.next();

		System.out.print("Enter your email address: ");
		String email = scanner.next();

		Optional<UserData> foundUser = getUserByNameAndEmail(name, email);

		if (foundUser.isPresent()) {
			int userID = foundUser.get().getUserID();
			System.out.println("Your User ID is: " + userID);
		} else {
			System.out.println("User not found.");
		}
	}

	private static Optional<UserData> getUserByNameAndEmail(String name, String email) {
		for (UserData userData : userDataMap.values()) {
			if (userData.getName().equalsIgnoreCase(name) && userData.getEmail().equalsIgnoreCase(email)) {
				return Optional.of(userData);
			}
		}
		return Optional.empty();
	}

	private static void viewScoreboard(int loginUserID) {
		System.out.println("\nScoreboard:");

		// Find the user with the matching login User ID
		UserData loginUser = userDataMap.get(loginUserID);

		if (loginUser != null) {
			System.out.println("User ID: " + loginUser.getUserID());
			System.out.println("Name: " + loginUser.getName());
			System.out.println("Email: " + loginUser.getEmail());
			System.out.println("High Score: " + loginUser.getHighScore());
			System.out.println("Total Score: " + loginUser.getTotalScore());
			System.out.println("Games Played: " + loginUser.getGamesPlayed());
		} else {
			System.out.println("User not found.");
		}

		System.out.println("------------------------------");
	}

	private static void addNewUser(Scanner scanner) {
		System.out.print("Enter your name: ");
		String name = scanner.next();

		System.out.print("Enter your email address: ");
		String email = scanner.next();

		// Check if the email follows the pattern.
		if (!email.endsWith("@gmail.com")) {
			System.out.println("Invalid email format. You must have an email ending with '@gmail.com'.");
			return;
		}

		Optional<UserData> existingUser = getUserByEmail(email);

		if (existingUser.isPresent()) {
			System.out.println("User with this email already exists.");
		} else {
			int userID = generateUserID();
			userDataMap.put(userID, new UserData(userID, name, email));
			System.out.println("User registered successfully! Your User ID is: " + userID);
		}
	}

	private static Optional<UserData> getUserByEmail(String email) {
		for (UserData userData : userDataMap.values()) {
			if (userData.getEmail().equalsIgnoreCase(email)) {
				return Optional.of(userData);
			}
		}
		return Optional.empty();
	}

	private static int generateUserID() {
		return currentUserID++;
	}

	private static void playGame(Scanner scanner) {
		if (loginUserID == -1) {
			System.out.println("Please log in to play the game...");
			return;
		}

		System.out.println("Welcome to the Number Game!");

		int wrongAttempts = 0;

		while (true) {
			System.out.println("Choose the difficulty level:");
			System.out.println("1. Easy (1-50)");
			System.out.println("2. Medium (1-100)");
			System.out.println("3. Hard (1-200)");
			int difficultyChoice = getChoice(scanner, 1, Integer.MAX_VALUE);

			switch (difficultyChoice) {
			case 1:
				playRound(scanner, 1, 50);
				break;
			case 2:
				playRound(scanner, 1, 100);
				break;
			case 3:
				playRound(scanner, 1, 200);
				break;
			default:
				System.out.println("Invalid choice. Please select a valid difficulty.");
				wrongAttempts++;

				if (wrongAttempts >= 3) {
					System.out.println("You've made three wrong choices in a row. Returning to the main menu.");
					return; // Return to the main menu after three wrong choices
				}
				break;
			}

	
			System.out.print("Do you want to play another round? (yes/no): ");
			String playAgain = scanner.next().toLowerCase();

			if (!playAgain.equals("yes")) {
				break; // If the player doesn't want to play again, exit the loop
			}
		}
	}

	private static void playRound(Scanner scanner, int lowerLimit, int upperLimit) {
	    int numberToGuess = generateRandomNumber(lowerLimit, upperLimit);
	    int attempts = 0;
	    int maxAttempts = 10; // Set the maximum number of attempts here

	    System.out.println(
	        "\nI'm thinking of a number between " + lowerLimit + " and " + upperLimit + ". Try to guess it!");

	    while (attempts < maxAttempts) {
	        System.out.print("Enter your guess: ");
	        int userGuess = getChoice(scanner, lowerLimit, upperLimit);
	        attempts++;

	        if (userGuess == numberToGuess) {
	            System.out.println("Congratulations! You guessed the correct number (" + numberToGuess + ") in "
	                    + attempts + " attempts.");

	
	            if (attempts == 1) {
	                System.out.println("Wow, you're a genius!");
	            } else if (attempts <= 5) {
	                System.out.println("Great job!");
	            } else if (attempts <= 10) {
	                System.out.println("You did well!");
	            } else {
	                System.out.println("It took you a few tries, but you got it!");
	            }

	            // Update the user's score
	            int userID = loginUserID; // Use the logged-in user's ID
	            UserData user = userDataMap.get(userID);

	            if (user != null) {
	                user.addGameScore(attempts);

	                // Check and update the high score if necessary
	                if (attempts < user.getHighScore() || user.getHighScore() == 0) {
	                    user.setHighScore(attempts);
	                }

	                user.incrementGamesPlayed();
	            } else {
	                System.out.println("User not found.");
	            }

	            // Save user data
	            saveUserData();
	            break;
	        } else if (userGuess < numberToGuess) {
	            System.out.println("Too low. Try again.");
	        } else {
	            System.out.println("Too high. Try again.");
	        }
	    }

	    if (attempts >= maxAttempts) {
	        System.out.println("You've reached the maximum number of attempts for this round.");
	        System.out.println("The correct number was: " + numberToGuess);
	    }
	}

	private static int getUserIDByName(int userID) {
		for (UserData userData : userDataMap.values()) {
			if (userData.getName().equalsIgnoreCase("YourName")) {
				// Replace "YourName" with the name you want to match.
				return userData.getUserID();
			}
		}
		return -1;
	}

	private static int generateRandomNumber(int lowerLimit, int upperLimit) {
		Random random = new Random();
		return random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
	}

	private static int getChoice(Scanner scanner, int min, int max) {
		int choice;
		while (true) {
			System.out.print("Enter your choice (" + min + "-" + max + "): ");
			try {
				choice = Integer.parseInt(scanner.next());
				if (choice < min || choice > max) {
					System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
				} else {
					return choice;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}
	}

	// Add this method to your NumberGame class
	private static void showInstructions() {
		System.out.println("\nNumber Game Tutorial");
		System.out.println("----------------------------");
		System.out.println("Welcome to the Number Game! The goal of the game is to guess a randomly generated number.");
		System.out.println("Here's how to play:");
		System.out.println("1. Choose a difficulty level: Easy, Medium, or Hard.");
		System.out.println("2. You will be provided with a range of numbers based on the chosen difficulty.");
		System.out.println("3. You need to guess the correct number within the given range.");
		System.out.println("4. After each guess, you will receive feedback: Too low, Too high, or Correct.");
		System.out.println("5. Try to guess the number in as few attempts as possible to get a high score.");
		System.out.println("6. Your high score will be recorded in the scoreboard.");
		System.out.println("7. You can play as many rounds as you like. Have fun!");
		System.out.println("----------------------------");
	}

}

class UserData {
	private int userID;
	private String name;
	private String email;
	private int highScore;
	private int totalScore;
	private int gamesPlayed;

	public UserData(int userID, String name, String email) {
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.highScore = 0;
		this.totalScore = 0;
		this.gamesPlayed = 0;
	}

	public UserData(int userID, String name, String email, int highScore, int totalScore, int gamesPlayed) {
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.highScore = highScore;
		this.totalScore = totalScore;
		this.gamesPlayed = gamesPlayed;
	}

	public int getUserID() {
		return userID;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void addGameScore(int score) {
		totalScore += score;
		gamesPlayed++;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void incrementGamesPlayed() {
		gamesPlayed++;
	}
}
