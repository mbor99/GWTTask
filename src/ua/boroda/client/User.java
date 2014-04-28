package ua.boroda.client;

public class User {

    public User(String id, String name, String surname, String email, role r) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.r = r;
    }


    String id;
    String name;
    String surname;
    String email;

    public enum role {admin, user}

    role r;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public role getR() {
        return r;
    }


}
