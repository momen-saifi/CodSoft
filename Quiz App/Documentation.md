
# Java Quiz Application Documentation

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Installation](#installation)
- [Usage](#usage)
  - [Main Menu](#main-menu)
  - [Admin Menu](#admin-menu)
  - [User Menu](#user-menu)
- [Data Persistence](#data-persistence)
- [Contributing](#contributing)
- [License](#license)

## Overview
The Java Quiz Application is a console-based quiz management system. It allows users to answer multiple-choice questions, maintain user profiles with scores, and provides administrative functions for managing quiz questions.

## Features
- **User Authentication:** Users and administrators can log in to the application.
- **User Profiles:** Users have profiles with usernames, passwords, and scores.
- **Quiz Questions:** Administrators can add and remove multiple-choice quiz questions.
- **Quiz Taking:** Users can take quizzes with timed questions.
- **Scoring:** Users' scores are recorded and displayed.
- **Password Change:** Users can change their passwords.

## Prerequisites
Before running the Java Quiz Application, make sure you have the following installed:

- Java Development Kit (JDK) 8 or higher.
- A Java-compatible development environment or IDE.

## Getting Started
1. Clone or download the Java Quiz Application code to your computer.

### Installation
2. Open a terminal or command prompt and navigate to the directory containing the downloaded code.

3. Compile the application by running the following command:

   ```shell
   javac QuizApp.java
   ```

## Usage
### Main Menu
When you start the application, you'll see the main menu with the following options:
1. Admin Login: Log in as an administrator (Username: admin, Password: admin).
2. User Login: Log in as a user with an existing account.
3. Exit: Close the application.

### Admin Menu
If you log in as an administrator, you'll have access to the admin menu:
1. Add Question: Add a new quiz question.
2. Remove Question: Remove a quiz question.
3. View Questions: View all quiz questions.
4. Create User: Create a new user account.
5. Logout: Log out from the admin account.

### User Menu
If you log in as a user, you'll have access to the user menu:
1. Take Quiz: Take the quiz, answering timed questions.
2. View Score: View your quiz score.
3. Change Password: Change your account password.
4. Logout: Log out from your user account.

## Data Persistence
- Quiz questions and user profiles are saved to a file named "quiz_data.dat" for data persistence. Make sure not to delete this file.

## Contributing
Feel free to contribute to the development of this application by submitting pull requests or reporting issues.

## License
This Java Quiz Application is open-source software licensed under the MIT License. 

## Methods
Here, we provide an overview of the methods used in the Java Quiz Application to give users and developers a deeper understanding of how the application works:

### `adminLogin` Method
- **Description:** Allows administrators to log in to the application using a username and password.
- **Usage:** Administrators have access to functions like adding, removing, viewing questions, creating users, and logging out.

### `addQuestion` Method
- **Description:** Enables the administrator to add a new quiz question with multiple-choice options and the correct answer.
- **Usage:** The added question is saved to the data file.

### `removeQuestion` Method
- **Description:** Allows the administrator to remove a quiz question by selecting it from a list of questions.
- **Usage:** The removed question is deleted from the data file.

### `viewQuestions` Method
- **Description:** Displays a list of all quiz questions along with their options.
- **Usage:** Users can view the quiz questions.

### `saveData` Method
- **Description:** Saves the current state of both questions and user profiles to a data file named "quiz_data.dat."

### `loadData` Method
- **Description:** Loads data from the "quiz_data.dat" file into the application, populating the questions and user profiles.

### `createUser` Method
- **Description:** Allows the administrator to create a new user profile with a username and password.
- **Usage:** The created user is saved to the data file.

### `userLogin` Method
- **Description:** Enables users to log in using their username and password.
- **Usage:** Once logged in, users can take the quiz, view their scores, change their passwords, and log out.

### `takeQuiz` Method
- **Description:** Conducts the quiz, presenting questions and recording user answers and scores

.
- **Usage:** Allows users to choose between admin login, user login, or exiting the application.
