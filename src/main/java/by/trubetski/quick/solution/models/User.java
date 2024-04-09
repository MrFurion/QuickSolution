package by.trubetski.quick.solution.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Name should be not empty")
    @Size(min = 4, max = 30, message = "Name should be between 4 and 30 character")
    private String username;
    @Column(name = "email")
    @Email(message = "Please write a correct email, for example mrsmith@email.com")
    @NotEmpty(message = "Email should be not empty")
    private String email;
    @Column(name = "password")
    @NotEmpty(message = "Password should be not empty")
    private String password;
    @Column(name = "type")
    private String type;
    @OneToMany(mappedBy = "owner")
    private List<Orders> orders;
    @OneToOne(mappedBy = "users")
    private CourierInf courierInf;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}