import java.io.*;
import java.util.*;

class Student implements Serializable {
	 private static final long serialVersionUID = 1L;
    private int rollNumber;
    private String name;
    private int[] marks;
   
    private int totalMarks;
    private double averagePercentage;
    private char grade;

    public Student(int rollNumber, String name, int[] marks) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.marks = marks;
    }

    public void calculateResult() {
        totalMarks = Arrays.stream(marks).sum();
        averagePercentage = (double) totalMarks / marks.length;

        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public int[] getMarks() {
        return marks;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public double getAveragePercentage() {
        return averagePercentage;
    }

    public char getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Roll Number: " + rollNumber +
               "\nName: " + name +
               "\nTotal Marks: " + totalMarks +
               "\nAverage Percentage: " + averagePercentage +
               "\nGrade: " + grade;
    }
}

public class StudentGradeCalculator {
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Options:");
            System.out.println("1. Add Student");
            System.out.println("2. Calculate Grades");
            System.out.println("3. Display Results");
            System.out.println("4. Save Data");
            System.out.println("5. Load Data");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    calculateGrades();
                    break;
                case 3:
                    displayResults(scanner);
                    break;
                case 4:
                    saveData();
                    break;
                case 5:
                    loadData();
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter Roll Number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Number of Subjects: ");
        int numSubjects = scanner.nextInt();
        int[] marks = new int[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter Marks for Subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }
        Student newStudent = new Student(rollNumber, name, marks);
        newStudent.calculateResult();
        students.add(newStudent);
    }

    private static void calculateGrades() {
        for (Student student : students) {
            student.calculateResult();
        }
        System.out.println("Grades calculated for all students.");
    }

    private static void displayResults(Scanner scanner) {
        System.out.println("Options:");
        System.out.println("1. Search by Roll Number");
        System.out.println("2. Search by Name");
        int searchChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (searchChoice == 1) {
            System.out.print("Enter Roll Number to Find: ");
            int searchRollNumber = scanner.nextInt();
            displayStudentDetails(searchRollNumber);
        } else if (searchChoice == 2) {
            System.out.print("Enter Name to Find: ");
            String searchName = scanner.nextLine();
            displayStudentDetails(searchName);
        } else {
            System.out.println("Invalid search option.");
        }
    }

    private static void displayStudentDetails(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    private static void displayStudentDetails(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student with name " + name + " not found.");
    }

    private static void saveData() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("student_database.dat"))) {
            outputStream.writeObject(students);
           // System.out.println("Data saved to student_database.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadData() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("student_database.dat"))) {
            students = (List<Student>) inputStream.readObject();
            //System.out.println("Data loaded from student_database.dat");
        } catch (IOException | ClassNotFoundException e) {
            //System.out.println("Data file not found or corrupted.");
        }
    }
}
