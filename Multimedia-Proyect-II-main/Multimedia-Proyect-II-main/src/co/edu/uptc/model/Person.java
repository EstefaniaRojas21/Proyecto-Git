package co.edu.uptc.model;


public abstract class Person {

    public abstract boolean couldLogIn(String user, String password);
    
    private String firstName;
    private String lastName;
    private String user;
    private String password;
    private int id;

    public Person(){}

    public Person(String firstName, String lastName, int id, String user, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.password = password;
        this.id = id;
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
