import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImp implements InterfaceDao {
    private final String url;
    private final String username;
    private final String password;

    public StudentDaoImp(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM student_table";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int studentId = resultSet.getInt("Student_id");
                String firstName = resultSet.getString("First_name");
                String lastName = resultSet.getString("Last_name");
                double marks = resultSet.getDouble("Marks");
                String address = resultSet.getString("Address");

                Student student = new Student(studentId, firstName, lastName, marks, address);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Student getStudentById(int studentId) {
        Student student = null;
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM student_table WHERE Student_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("First_name");
                String lastName = resultSet.getString("Last_name");
                double marks = resultSet.getDouble("Marks");
                String address = resultSet.getString("Address");

                student = new Student(studentId, firstName, lastName, marks, address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Student insertStudent(Student student) {
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO student_table (First_name, Last_name, Marks, Address) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setDouble(3, student.getMarks());
            statement.setString(4, student.getAddress());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int studentId = generatedKeys.getInt(1);
                student.setStudentId(studentId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Student deleteStudent(int studentId) {
        Student deletedStudent = null;
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM student_table WHERE Student_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, studentId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                deletedStudent = new Student();
                deletedStudent.setStudentId(studentId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletedStudent;
    }

    @Override
    public Student updateStudent(int studentId,Student updatedStudent) {
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE student_table SET First_name = ?, Last_name = ?, Marks = ?, Address = ? WHERE Student_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, updatedStudent.getFirstName());
            statement.setString(2, updatedStudent.getLastName());
            statement.setDouble(3, updatedStudent.getMarks());
            statement.setString(4, updatedStudent.getAddress());
            statement.setInt(5, studentId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                updatedStudent.setStudentId(studentId);
                return updatedStudent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String studentsListToJson(List<Student> students) {
        return JsonConverter.studentsListToJson(students);
    }


    public String studentToJson(Student student) {
        return JsonConverter.studentToJson(student);
    }

}

