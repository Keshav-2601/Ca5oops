public interface StuduentFilter {
    boolean filter(Student student);

    float getMinMarks();
    void setMinMarks(float minMarks);

    float getMaxMarks();
    void setMaxMarks(float maxMarks);

    String getAddress();
    void setAddress(String address);
}
