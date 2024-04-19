import java.util.List;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Students";
        String username = "root";
        String password = "";
        InterfaceDao studentDao = new StudentDaoImp(url, username, password);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu");
            System.out.println("1: View students");
            System.out.println("2: Add new student");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    List<Student> students = studentDao.getAllStudents();
                    for (Student student : students) {
                        System.out.println(student);
                    }
                    break;

                case 2:
                    System.out.println("Enter student id");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();

                    Student foundStudent = studentDao.getStudentById(studentId);
                    if (foundStudent != null) {
                        System.out.println("Found student:");
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("Student not found with id: " + studentId);
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
