package entities;

import anotations.Column;
import anotations.Entity;
import anotations.Id;

import java.time.LocalDate;

@Entity(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "age")
    private long age;

    @Column(name = "registration_date")
    private LocalDate resignationDate;

    public User(String username, int age, LocalDate resignationDate) {
        this.username = username;
        this.age = age;
        this.resignationDate = resignationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public LocalDate getResignationDate() {
        return resignationDate;
    }

    public void setResignationDate(LocalDate resignationDate) {
        this.resignationDate = resignationDate;
    }
}
