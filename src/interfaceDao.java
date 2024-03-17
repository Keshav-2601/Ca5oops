import java.util.List;
public interface interfaceDao {
    List<student> getAllstudents();
    student getstudentById(int studentId);

    student insertstudent();

    student deletestudent(int student_Id);
    student updateStudent(int student_Id);
}
