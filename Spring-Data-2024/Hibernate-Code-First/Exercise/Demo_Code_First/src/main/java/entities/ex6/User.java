package entities.ex6;

import entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column(name = "full_name")
    private String fullName;
    @Column
    private double balance;
}
