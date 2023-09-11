import java.io.*;
import java.util.*;

class Course implements Serializable {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode +
               "\nTitle: " + title +
               "\nDescription: " + description +
               "\nCapacity: " + capacity +
               "\nSchedule: " + schedule;
    }

    private static final long serialVersionUID = 1L;
}

class Student implements Serializable {
    private int studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerForCourse(Course course) {
        registeredCourses.add(course);
    }

    public void dropCourse(Course course) {
        registeredCourses.remove(course);
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID +
               "\nName: " + name;
    }

    private static final long serialVersionUID = 1L;
}

class CourseDatabase {
    private List<Course> courses;

    public CourseDatabase() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }
}

class StudentDatabase {
    private List<Student> students;

    public StudentDatabase() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }
}

public class CourseRegistrationApp {
    private CourseDatabase courseDB;
    private StudentDatabase studentDB;
    private Scanner scanner;

    private static final String COURSE_DATA_FILE = "courses.txt";
    private static final String STUDENT_DATA_FILE = "students.txt";

    public CourseRegistrationApp() {
        this.courseDB = new CourseDatabase();
        this.studentDB = new StudentDatabase();
        this.scanner = new Scanner(System.in);
    }

    public void saveDataToFile() {
        try {
            // Save course data
            ObjectOutputStream courseOutput = new ObjectOutputStream(new FileOutputStream(COURSE_DATA_FILE));
            courseOutput.writeObject(courseDB.getAllCourses());
            courseOutput.close();

            // Save student data
            ObjectOutputStream studentOutput = new ObjectOutputStream(new FileOutputStream(STUDENT_DATA_FILE));
            studentOutput.writeObject(studentDB.getAllStudents());
            studentOutput.close();

            //System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }

    public void loadDataFromFile() {
        try {
            // Load course data
            ObjectInputStream courseInput = new ObjectInputStream(new FileInputStream(COURSE_DATA_FILE));
            List<Course> courses = (List<Course>) courseInput.readObject();
            courseDB.getAllCourses().addAll(courses);
            courseInput.close();

            // Load student data
            ObjectInputStream studentInput = new ObjectInputStream(new FileInputStream(STUDENT_DATA_FILE));
            List<Student> students = (List<Student>) studentInput.readObject();
            studentDB.getAllStudents().addAll(students);
            studentInput.close();

          //  System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data from file: " + e.getMessage());
        }
    }

    public void run() {
        while (true) {
            System.out.println("Course Registration System");
            System.out.println("1. Add Course");
            System.out.println("2. List All Courses");
            System.out.println("3. Add Student");
            System.out.println("4. List All Students");
            System.out.println("5. Register Student for Course");
            System.out.println("6. Drop Student from Course");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    listAllCourses();
                    break;
                case 3:
                    addStudent();
                    break;
                case 4:
                    listAllStudents();
                    break;
                case 5:
                    registerStudentForCourse();
                    break;
                case 6:
                    dropStudentFromCourse();
                    break;
                case 7:
                    System.out.println("Exiting the system. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public void addCourse() {
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Course Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Course Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Course Schedule: ");
        String schedule = scanner.nextLine();

        Course course = new Course(courseCode, title, description, capacity, schedule);
        courseDB.addCourse(course);
        System.out.println("Course added successfully.");
    }

    public void listAllCourses() {
        List<Course> courses = courseDB.getAllCourses();
        System.out.println("All Courses:");
        for (Course course : courses) {
            System.out.println(course);
            System.out.println("-------------------------");
        }
    }

    public void addStudent() {
        System.out.print("Enter Student ID: ");
        int studentID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        Student student = new Student(studentID, name);
        studentDB.addStudent(student);
        System.out.println("Student added successfully.");
    }

    public void listAllStudents() {
        List<Student> students = studentDB.getAllStudents();
        System.out.println("All Students:");
        for (Student student : students) {
            System.out.println(student);
            System.out.println("Registered Courses:");
            List<Course> registeredCourses = student.getRegisteredCourses();
            if (registeredCourses.isEmpty()) {
                System.out.println("No courses registered.");
            } else {
                for (Course course : registeredCourses) {
                    System.out.println(course.getTitle());
                }
            }
            System.out.println("-------------------------");
        }
    }

    public void registerStudentForCourse() {
        System.out.print("Enter Student ID: ");
        int studentID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Course Code to Register: ");
        String courseCode = scanner.nextLine();

        Student student = findStudentById(studentID);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            if (student.getRegisteredCourses().size() < 5) { // Limiting each student to 5 courses
                student.registerForCourse(course);
                System.out.println(student.getName() + " registered for " + course.getTitle());
            } else {
                System.out.println("Student has already registered for 5 courses. Cannot register for more.");
            }
        } else {
            System.out.println("Student or course not found. Registration failed.");
        }
    }

    public void dropStudentFromCourse() {
        System.out.print("Enter Student ID: ");
        int studentID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Course Code to Drop: ");
        String courseCode = scanner.nextLine();

        Student student = findStudentById(studentID);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            if (student.getRegisteredCourses().contains(course)) {
                student.dropCourse(course);
                System.out.println(student.getName() + " dropped from " + course.getTitle());
            } else {
                System.out.println(student.getName() + " is not registered for " + course.getTitle());
            }
        } else {
            System.out.println("Student or course not found. Drop failed.");
        }
    }

    private Student findStudentById(int studentID) {
        List<Student> students = studentDB.getAllStudents();
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    private Course findCourseByCode(String courseCode) {
        List<Course> courses = courseDB.getAllCourses();
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        CourseRegistrationApp app = new CourseRegistrationApp();
        app.loadDataFromFile(); // Load existing data (if any) from files
        app.run();
        app.saveDataToFile(); // Save data to files before exiting
    }
}
