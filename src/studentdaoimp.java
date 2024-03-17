import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class studentdaoimp implements interfaceDao {


    @Override
    public List<student> getAllstudents() {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Students", "root", "");
            System.out.println(con);

            String query1="Select * from Student.student_table";
            Statement s1= con.createStatement();
            ResultSet result= s1.executeQuery(query1);
            List<student> stu=new ArrayList<>();
            while(result.next()){
                int student_id=result.getInt("Student_id");
                String firstname=result.getString("First_name");
                String lastname=result.getString("LastName");
                Double Marks=result.getDouble("Marks");
                String Address=result.getString("Address");
                student st=new student(firstname,lastname,Marks,Address);
                stu.add(st);
            }
        }
        catch (Exception e){

        }
        return null;
    }

    @Override
    public student getstudentById(int studentId) {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Students", "root", "");
            System.out.println(con);
            String query1="select * from Student.student_table where id="+studentId;
            Statement s1= con.createStatement();
            ResultSet result =s1.executeQuery(query1);
            if(result.next()){
                int student_id=result.getInt("Student_id");
                String firstname=result.getString("First_name");
                String lastname=result.getString("LastName");
                Double Marks=result.getDouble("Marks");
                String Address=result.getString("Address");
                student std = new student(firstname, lastname, Marks, Address);
            }
        } catch(Exception e){

        }
        return null;
    }

    @Override
    public student insertstudent() {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Students", "root", "");
            System.out.println(con);
            String Query1="INSERT INTO Students.student_table(First_name,LastName,Marks,Address) VALUES (?, ?, ?, ?) ";
            PreparedStatement p1= con.prepareStatement(Query1);
            p1.setString(1,"BABA");
            p1.setString(2,"YAGA");
            p1.setDouble(3,67.5);
            p1.setString(4,"34,Romania st john road");
            p1.executeUpdate();

            Statement s1= con.createStatement();;
            ResultSet result=s1.executeQuery("select * from student_table");
            while(result.next()){
                int student_id=result.getInt("Student_id");
                String firstname=result.getString("First_name");
                String lastname=result.getString("LastName");
                Double Marks=result.getDouble("Marks");
                String Address=result.getString("Address");
                student std = new student(firstname, lastname, Marks, Address);
            }

        }catch(Exception e){

        }
        return null;
    }

    @Override
    public student deletestudent(int student_Id) {
        try{

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Students", "root", "");
                System.out.println(con);
                String Query1="Delete from Student.student_table where id=?";

                PreparedStatement p1= con.prepareStatement(Query1);
                p1.setInt(1,student_Id);
                p1.executeUpdate();
                List<student> liststd=new ArrayList<>();
                Statement s1= con.createStatement();
                ResultSet result=s1.executeQuery("Select * from student_table");
                while(result.next()){
                    int student_id=result.getInt("Student_id");
                    String firstname=result.getString("First_name");
                    String lastname=result.getString("LastName");
                    Double Marks=result.getDouble("Marks");
                    String Address=result.getString("Address");
                    student std = new student(firstname, lastname, Marks, Address);
                    liststd.add(std);
                }

        }catch(Exception e){
               System.out.println("invalid student_id");
        }
        return null;
    }
    public student updateStudent(int student_Id){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Students", "root", "");
            System.out.println(con);
            Scanner sc=new Scanner("system.in");
            String fname=sc.next();
            String sname=sc.next();
            int marks=sc.nextInt();
            String address=sc.next();
            String query1="Update stuednt_table SET First_name = ?, LastName = ?, Marks = ?, Address = ?";
            PreparedStatement p1= con.prepareStatement(query1);
            p1.setString(1,fname);
            p1.setString(2,sname);
            p1.setInt(3,marks);
            p1.setString(4,address);
            int rowsupdate=p1.executeUpdate();
            System.out.println("Rows updated:"+rowsupdate);
        }
        catch (Exception e){

        }
        return null;
    }
    public List<student> filter(StudentFilter filter) {
        List<student> filteredStudents = new ArrayList<>();
        String sql = "SELECT * FROM Student_table WHERE marks >= ? AND marks <= ? AND address = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setFloat(1, filter.getMinMarks());
            statement.setFloat(2, filter.getMaxMarks());
            statement.setString(3, filter.getAddress());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int studentId = resultSet.getInt("studentId");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                float marks = resultSet.getFloat("marks");
                String address = resultSet.getString("address");

                student student = new student(Student_Id, First_name, LastName, Marks, Address);
                filteredStudents.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filteredStudents;
    }
}
