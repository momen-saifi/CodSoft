
# Course Registration App Documentation

## Introduction

The Course Registration App is a Java-based application for managing courses and student registrations. It allows you to create and manage courses, add students, and register/drop students from courses.

## Features

- **Add and Manage Courses:** Create and manage courses, including their details such as code, title, description, capacity, and schedule.
- **Add and Manage Students:** Add and manage students, including their details such as student ID and name.
- **Register Students for Courses:** Register students for courses, ensuring they don't exceed the course capacity.
- **Drop Students from Courses:** Drop students from courses when needed.
- **View Course List:** View a list of all courses, including their details.
- **View Student List:** View a list of all students, including their registered courses.

## Classes

### Course Class

The `Course` class represents a course and has the following attributes:

- `courseCode` (String): A unique code for the course.
- `title` (String): The title or name of the course.
- `description` (String): A brief description of the course.
- `capacity` (int): The maximum number of students that can enroll in the course.
- `schedule` (String): The schedule or timing of the course.

The `Course` class also provides getter methods for accessing these attributes and overrides the `toString()` method to provide a formatted string representation of the course.

### Student Class

The `Student` class represents a student and has the following attributes:

- `studentID` (int): A unique identifier for the student.
- `name` (String): The name of the student.
- `registeredCourses` (List<Course>): A list of courses that the student is registered for.

The `Student` class provides methods for registering and dropping courses and overrides the `toString()` method to provide a formatted string representation of the student.

### CourseDatabase Class

The `CourseDatabase` class is responsible for storing and managing courses. It has the following methods:

- `addCourse(Course course)`: Adds a course to the database.
- `getAllCourses()`: Retrieves a list of all courses in the database.
- `removeCourse(Course course)`: Removes a course from the database.

### StudentDatabase Class

The `StudentDatabase` class is responsible for storing and managing students. It has the following methods:

- `addStudent(Student student)`: Adds a student to the database.
- `getAllStudents()`: Retrieves a list of all students in the database.
- `removeStudent(Student student)`: Removes a student from the database.

### CourseRegistrationApp Class

The `CourseRegistrationApp` class is the main class that drives the application. It provides the following functionality:

- Adding courses to the course database.
- Listing all courses.
- Adding students to the student database.
- Listing all students along with their registered courses.
- Registering students for courses.
- Dropping students from courses.
- A menu-driven user interface for interacting with the application.

## How to Use

To use the Course Registration App, follow these steps:

1. Compile the code using a Java compiler:

   ```shell
   javac CourseRegistrationApp.java
   ```

2. Run the application:

   ```shell
   java CourseRegistrationApp
   ```

3. Follow the on-screen menu to perform various actions, such as adding courses, adding students, registering students for courses, and more.

## Data Storage

The application stores data in-memory, so all data will be lost when you exit the application. Consider implementing data persistence to store data between application runs.

## Contributing

You are welcome to contribute to the development of this application by submitting pull requests or reporting issues on the GitHub repository.

## License

This Course Registration App is open-source software licensed under the MIT License.

## Credits

This application was created by Momen and is maintained by the community of contributors.

---

Feel free to include this documentation along with your code to provide users and developers with essential information about the application, its features, usage instructions, and licensing details.
