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
}
