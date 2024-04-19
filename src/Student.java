public class Student {
    private int studentId; // Add studentId field
    private String firstName; // Corrected naming convention
    private String lastName;
    private double marks; // Changed to double for consistency with database
    private String address;

    // Constructors
    public Student() {}

    public Student(String firstName, String lastName, double marks, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.marks = marks;
        this.address = address;
    }

    public Student(int studentId, String firstName, String lastName, double marks, String address) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.marks = marks;
        this.address = address;
    }

    // Getters and setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // toString method
    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Name: " + firstName + " " + lastName + ", Marks: " + marks + ", Address: " + address;
    }
}
