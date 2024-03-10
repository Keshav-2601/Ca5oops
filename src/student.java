import java.sql.*;
import java.util.Scanner;

public class student {
    String firstname;
    String lastname;
    Double Marks;
    String Address;

    public student(){

    }
    public student(String fn,String ln,Double marks,String Address){
        this.firstname=fn;
        this.lastname=ln;
        this.Marks=marks;
        this.Address=Address;
    }

    @Override
    public String toString() {
        return firstname+" "+lastname+" "+Marks+" "+Address;
    }
    public void getallstudent() throws SQLException {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Students", "root", "");
            System.out.println(con);
            System.out.println("Welcome to student database app:");
            Scanner s = new Scanner(System.in);
            Statement s1= con.createStatement();;
            ResultSet result=s1.executeQuery("select * from student_table");
            while(result.next()){
                int student_id=result.getInt("Student_id");
                String firstname=result.getString("First_name");
                String lastname=result.getString("LastName");
                int Marks=result.getInt("Marks");
                String Address=result.getString("Address");
                System.out.print(student_id + ", ");
                System.out.print(firstname + ", ");
                System.out.print(lastname + ", ");
                System.out.println(Marks);
            }
        }
        catch (Exception e){

        }
    }
}
