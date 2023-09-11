import java.io.*;
import java.util.*;

class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    private String questionText;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String questionText, List<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private int score;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.score = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int amount) {
        score += amount;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }
}

public class QuizApp {
    private List<Question> questions;
    private List<User> users;
    private User currentUser;
    private Scanner scanner;

    public QuizApp() {
        questions = new ArrayList<>();
        users = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    public void adminLogin(String username, String password) {
        // Implement admin login logic here
        // Check if username and password match admin credentials
        if (username.equals("admin") && password.equals("admin")) {
            // Admin logged in successfully
            // Provide admin options
            while (true) {
                System.out.println("Admin Menu:");
                System.out.println("1. Add Question");
                System.out.println("2. Remove Question");
                System.out.println("3. View Questions");
                System.out.println("4. Create User"); // Option to create a new user
                System.out.println("5. Logout");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addQuestion();
                        break;
                    case 2:
                        removeQuestion();
                        break;
                    case 3:
                        viewQuestions();
                        break;
                    case 4:
                        System.out.print("Enter new user username: ");
                        String newUsername = scanner.nextLine();
                        System.out.print("Enter new user password: ");
                        String newPassword = scanner.nextLine();
                        createUser(newUsername, newPassword); // Call the createUser method
                        break;
                    case 5:
                        return; // Admin logout
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        } else {
            System.out.println("Admin login failed. Invalid credentials.");
        }
    }
    public void addQuestion() {
        System.out.print("Enter the question text: ");
        String questionText = scanner.nextLine();
        List<String> options = new ArrayList<>();
        System.out.println("Enter multiple-choice options (enter an empty line to stop):");
        while (true) {
            String option = scanner.nextLine();
            if (option.isEmpty()) {
                break;
            }
            options.add(option);
        }
        System.out.print("Enter the index of the correct option: ");
        int correctOptionIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (correctOptionIndex >= 0 && correctOptionIndex < options.size()) {
            Question newQuestion = new Question(questionText, options, correctOptionIndex);
            questions.add(newQuestion);
            System.out.println("Question added successfully.");
            saveData(); // Save both question and user data
        } else {
            System.out.println("Invalid correct option index.");
        }
    }

    public void removeQuestion() {
        System.out.println("Select a question to remove:");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i).getQuestionText());
        }
        int questionIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (questionIndex >= 1 && questionIndex <= questions.size()) {
            questions.remove(questionIndex - 1);
            System.out.println("Question removed successfully.");
        } else {
            System.out.println("Invalid question index.");
        }
    }

    public void viewQuestions() {
        System.out.println("Questions:");
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
            List<String> options = question.getOptions();
            for (int j = 0; j < options.size(); j++) {
                String option = options.get(j);
                System.out.println("  " + (char) ('A' + j) + ". " + option);
            }
        }
    }

    public void saveData() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("quiz_data.dat"))) {
            outputStream.writeObject(questions);
            outputStream.writeObject(users);
           // System.out.println("Data saved to quiz_data.dat");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public void loadData() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("quiz_data.dat"))) {
            questions = (List<Question>) inputStream.readObject();
            users = (List<User>) inputStream.readObject();
            //System.out.println("Data loaded from quiz_data.dat");
        } catch (FileNotFoundException e) {
           // System.out.println("Data file not found. Starting with empty data.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }

    public void createUser(String username, String password) {
        User newUser = new User(username, password);
        users.add(newUser);
        System.out.println("User created successfully.");
        saveData(); // Save both question and user data
    }

    public void userLogin(String username, String password) {
        // Implement user login logic here
        // Check if username and password match a user's credentials
        User user = getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("User logged in successfully. Welcome, " + user.getUsername() + "!");
            while (true) {
                System.out.println("User Menu:");
                System.out.println("1. Take Quiz");
                System.out.println("2. View Score");
                System.out.println("3. Change Password");
                System.out.println("4. Logout");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        takeQuiz();
                        break;
                    case 2:
                        viewScore();
                        break;
                    case 3:
                        changePassword();
                        break;
                    case 4:
                        currentUser = null; // User logout
                        System.out.println("User logged out.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        } else {
            System.out.println("User login failed. Invalid credentials.");
        }
    }

    public void takeQuiz() {
        if (currentUser == null) {
            System.out.println("You need to log in first.");
            return;
        }
        int score = 0;
        int questionNumber = 0;
        int totalTime = 60; // Total time for the quiz in seconds (adjust as needed)
        long startTime = System.currentTimeMillis();

        for (Question question : questions) {
            questionNumber++;
            System.out.println("Question " + questionNumber + ": " + question.getQuestionText());
            List<String> options = question.getOptions();

            // Display options
            for (int i = 0; i < options.size(); i++) {
                System.out.println((char) ('A' + i) + ". " + options.get(i));
            }

            // Set a timer for this question
            long questionStartTime = System.currentTimeMillis();
            long elapsedTime;

            // Allow the user to answer within the time limit
            while (true) {
                elapsedTime = (System.currentTimeMillis() - questionStartTime) / 1000;
                long remainingTime = totalTime - elapsedTime;

                if (remainingTime <= 0) {
                    System.out.println("Time's up! Moving to the next question.");
                    break;
                }

                System.out.print("Your choice (A/B/C/D) [" + remainingTime + " seconds left]: ");
                String userChoice = scanner.nextLine().toUpperCase();

                if (userChoice.equals("A") || userChoice.equals("B") || userChoice.equals("C") || userChoice.equals("D")) {
                    int userChoiceIndex = userChoice.charAt(0) - 'A';

                    if (userChoiceIndex == question.getCorrectOptionIndex()) {
                        System.out.println("Correct!");
                        score++;
                    } else {
                        System.out.println("Incorrect. The correct answer is: " + (char) ('A' + question.getCorrectOptionIndex()));
                    }

                    break; // Move to the next question
                } else {
                    System.out.println("Invalid choice. Please select A, B, C, or D.");
                }
            }
        }

        long quizEndTime = System.currentTimeMillis();
        long quizDuration = (quizEndTime - startTime) / 1000;
        System.out.println("Quiz completed in " + quizDuration + " seconds.");
        currentUser.increaseScore(score);
        System.out.println("Your score: " + score);
    }

    public void viewScore() {
        if (currentUser == null) {
            System.out.println("You need to log in first.");
            return;
        }

        System.out.println("Your score: " + currentUser.getScore());
    }
    public void changePassword() {
        if (currentUser == null) {
            System.out.println("You need to log in first.");
            return;
        }

        System.out.print("Enter your current password: ");
        String currentPassword = scanner.nextLine();

        if (currentUser.getPassword().equals(currentPassword)) {
            System.out.print("Enter your new password: ");
            String newPassword = scanner.nextLine();
            currentUser.setPassword(newPassword);
            System.out.println("Password changed successfully.");
            saveData(); // Save both question and user data
        } else {
            System.out.println("Invalid current password. Password change failed.");
        }
    }

    private User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        QuizApp quizApp = new QuizApp();
        quizApp.run();
    }

    public void run() {
        // Load data from files
        loadData();

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter admin username: ");
                    String adminUsername = scanner.nextLine();
                    System.out.print("Enter admin password: ");
                    String adminPassword = scanner.nextLine();
                    adminLogin(adminUsername, adminPassword);
                    break;
                case 2:
                    System.out.print("Enter your username: ");
                    String userUsername = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String userPassword = scanner.nextLine();
                    userLogin(userUsername, userPassword);
                    break;
                case 3:
                    // Save data to files and exit
                    saveData();
                    System.out.println("Exiting the quiz application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
