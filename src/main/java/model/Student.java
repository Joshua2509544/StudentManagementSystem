package model;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String course;
    private int year;

    // with id, used when loading from database
    public Student(int id, String firstName, String lastName, String email, String course, int year) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.course = course;
        this.year = year;
    }

    // no id, used when adding a new student
    public Student(String firstName, String lastName, String email, String course, int year) {
        this(0, firstName, lastName, email, course, year);
    }

    // getters
    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }
    public int getYear() { return year; }
    public String getFullName() { return firstName + " " + lastName; }

    // setters
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setCourse(String course) { this.course = course; }
    public void setYear(int year) { this.year = year; }
}
