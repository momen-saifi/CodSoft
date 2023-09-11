Number Guessing Game 
Table of Contents
	• Overview
	• Classes
		• NumberGame
		• UserData
		• Methods
		• main
		• loadUserData
		• saveUserData
		• login
		• getUserByIDAndName
		• forgotUserID
		• getUserByNameAndEmail
		• viewScoreboard
		• addNewUser
		• getUserByEmail
		• generateUserID
		• playGame
		• playRound
		• getUserIDByName
		• generateRandomNumber
		• getChoice
		• showInstructions
Overview

The Number Game is a console-based game where players guess a randomly generated number within a specified range. The game includes features like user authentication, score tracking, difficulty levels, and instructions on how to play.
Features
	• User registration: Players can register their names and email addresses to participate in the game.
	• Scoreboard: The game keeps track of each player's high score, total score, and the number of games played.
	• Difficulty levels: Players can choose from three difficulty levels: Easy, Medium, or Hard, each with its own range of numbers.
	• Random number generation: The game generates a random number within the chosen difficulty level's range for players to guess.
	• Feedback: After each guess, players receive feedback on whether their guess was too low, too high, or correct.
	• High score tracking: The game records and displays the player's high score, encouraging players to improve their performance.

Getting Started
Prerequisites
 • Java Development Kit (JDK) installed on your machine.
Installation
1.Clone this repository to your local machine:
	git clone https://github.com/momen-saifi/CodSoft.git 
2.Navigate to the project directory:
	cd CodSift
	cd Number game 
3.Compile the Java code:
	javac NumberGame.java 
Usage
 1.Run the game:

java NumberGame 
2.Follow the on-screen menu to navigate and play the game.

Game Instructions
1.Login: Register as a new user or login with your User ID and username.
2.View Scoreboard: Check your current score, including your high score, total score, and the number of games played.
3.Add New User: Register as a new user by providing your name and email address. Note that email addresses must end with "@gmail.com."
4.Forgot User ID: Retrieve your User ID by entering your name and email address.
5.Play Game: Choose a difficulty level (Easy, Medium, or Hard) and attempt to guess the randomly generated number within the specified range.
6.How to Play: Access a tutorial on how to play the Number Guessing Game.
7.Exit: Save your progress and exit the game.

Classes
NumberGame Class
•	The NumberGame class is the main class that orchestrates the Number Game application.
•	Key attributes:
•	DATA_FILE: A constant string representing the filename for storing user data.
•	userDataMap: A map that stores user data, where the key is the user ID and the value is a UserData object.
•	currentUserID: An integer representing the ID of the current user.
•	loginUserID: An integer representing the ID of the user who is currently logged in.
•	The class includes methods for user authentication, gameplay, and data management.
UserData Class
•	The UserData class represents user profiles with the following attributes:
•	userID: An integer containing the user's unique identifier.
•	name: A string containing the user's name.
•	email: A string containing the user's email address.
•	highScore: An integer representing the user's highest game score.
•	totalScore: An integer representing the user's total game score.
•	gamesPlayed: An integer representing the number of games played by the user.
•	The class includes constructors and methods for managing user data.

Methods
main Method
•	public static void main(String[] args)
•	The entry point of the application.
•	Displays the main menu and allows users to navigate the game's features.
loadUserData Method
•	private static void loadUserData()
•	Loads user data from the "userdata.txt" file into the application.
•	Populates the userDataMap with user profiles.
saveUserData Method
•	private static void saveUserData()
•	Saves the current state of user data to the "userdata.txt" file.
login Method
•	private static void login(Scanner scanner)
•	Allows users to log in by entering their User ID and username.
•	Sets the loginUserID when a user successfully logs in.
getUserByIDAndName Method
•	private static Optional<UserData> getUserByIDAndName(int userID, String username)
•	Retrieves a user profile by User ID and username.
•	Returns an Optional containing the user profile if found, or an empty Optional if not found.
forgotUserID Method
•	private static void forgotUserID(Scanner scanner)
•	Allows users to retrieve their User ID by providing their name and email address.
getUserByNameAndEmail Method
•	private static Optional<UserData> getUserByNameAndEmail(String name, String email)
•	Retrieves a user profile by name and email address.
•	Returns an Optional containing the user profile if found, or an empty Optional if not found.
viewScoreboard Method
•	private static void viewScoreboard(int loginUserID)
•	Displays the scoreboard for the logged-in user, showing their user details and scores.
addNewUser Method
•	private static void addNewUser(Scanner scanner)
•	Allows users to register as a new user by providing their name and email address.
•	Validates the email format and checks for existing users with the same email.
getUserByEmail Method
•	private static Optional<UserData> getUserByEmail(String email)
•	Retrieves a user profile by email address.
•	Returns an Optional containing the user profile if found, or an empty Optional if not found.
generateUserID Method
•	private static int generateUserID()
•	Generates a new unique User ID for a newly registered user.
playGame Method
•	private static void playGame(Scanner scanner)
•	Initiates gameplay by allowing users to select a difficulty level and play rounds of the Number Game.
playRound Method
•	private static void playRound(Scanner scanner, int lowerLimit, int upperLimit)
•	Handles the gameplay logic for a single round of the Number Game.
•	Generates a random number within the specified range and allows the user to make guesses.
getUserIDByName Method
•	private static int getUserIDByName(int userID)
•	Retrieves a User ID by matching a user's name (replace "YourName" with the desired name).
generateRandomNumber Method
•	private static int generateRandomNumber(int lowerLimit, int upperLimit)
•	Generates a random number within a specified range.
getChoice Method
•	private static int getChoice(Scanner scanner, int min, int max)
•	Obtains a valid integer choice from the user within a specified range.
showInstructions Method
•	private static void showInstructions()
•	Displays the instructions on how to play the Number Game.


Contributing
Contributions to this game are welcome! If you'd like to contribute, please follow these steps:
1.	Fork the repository.
2.	Create a new branch for your feature or bug fix.
3.	Make your changes and commit them.
4.	Push your changes to your fork.
5.	Create a pull request against the main branch of this repository.
License
The Number Guessing Game is open-source software licensed under the MIT License.
Credits
This game was created by Momen and is maintained by the community of contributors.

