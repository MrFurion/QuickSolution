package by.trubetski.QuickSolution.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class Users {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should be not empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 character")
    private String name;

    @Column(name = "email")
    @Email(message = "Please write a correct email, for example mrsmith@email.com")
    @NotEmpty(message = "Email should be not empty")
    private String email;
    @Column(name = "password")
    @NotEmpty(message = "Password should be not empty")
    @Size(min = 4, max = 30, message = "Password should be between 4 and 30 character")
    private String password;

    @Column(name = "type")
    private String type;
    @OneToMany(mappedBy = "owner")
    private List<Orders> orders;

    @OneToOne(mappedBy = "users")
    private CourierInf courierInf;

    public Users() {
    }

    public Users(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
