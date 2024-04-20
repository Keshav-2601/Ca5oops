import java.util.List;

public interface InterfaceDao {
    List<Student> getAllStudents();
    Student getStudentById(int studentId);
    Student insertStudent(Student student);
    Student deleteStudent(int studentId);
    Student updateStudent(int studentId, Student updatedStudent);

}
