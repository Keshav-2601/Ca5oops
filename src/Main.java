import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Students", "root", "");
            System.out.println(con);
            System.out.println("Welcome to student database app:");
            Scanner s=new Scanner(System.in);
            while(true){
                System.out.println("Press 1 to getallstudent()");
                System.out.println("Press 2 to studentbyid()");
                System.out.println("Press 3 to update student name by id");
                System.out.println("Press 4 to insert  student details");
                System.out.println("Press 5 to delete  student name by id");
                System.out.println("Enter ur choice:");
                int choice=s.nextInt();
                if(choice==0){
                    System.out.println("User got exits");
                    break;
                }
                if(choice==1){
                    //get names
                    Statement s1= con.createStatement();;
                    ResultSet result=s1.executeQuery("select * from student_table");
                    while(result.next()){
                        int student_id=result.getInt("Student_id");
                        String firstname=result.getString("First_name");
                        String lastname=result.getString("LastName");
                        Double Marks=result.getDouble("Marks");
                        String Address=result.getString("Address");
                        System.out.print(student_id + ", ");
                        System.out.print(firstname + ", ");
                        System.out.print(lastname + ", ");
                        System.out.println(Marks+", ");
                        System.out.println(Address);
                    }
                }
                if(choice==2){
                    Scanner sc=new Scanner(System.in);
                    //get name by id
                    System.out.println("Enter the valid id ");
                    int id=sc.nextInt();
                    Statement s1= con.createStatement();
                    ResultSet result=s1.executeQuery("select * from student_table where Student_id="+id);
                    if(result.next()){
                        int student_id=result.getInt("Student_id");
                        String firstname=result.getString("First_name");
                        String lastname=result.getString("LastName");
                        Double Marks=result.getDouble("Marks");
                        String Address=result.getString("Address");
                        System.out.print(student_id + ", ");
                        System.out.print(firstname + ", ");
                        System.out.print(lastname + ", ");
                        System.out.println(Marks+", ");
                        System.out.println(Address);
                    }else {
                        System.out.println("ID does not exits:");
                    }
                }
                if(choice==3){
                    //delete data
                }
                if(choice==4){
                    //insert data
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
                        System.out.print(student_id + ", ");
                        System.out.print(firstname + ", ");
                        System.out.print(lastname + ", ");
                        System.out.println(Marks);
                    }
                }
            }
        }
        catch(Exception e){

        }


    }
}