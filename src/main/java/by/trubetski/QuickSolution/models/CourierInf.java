package by.trubetski.QuickSolution.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "courierinf")
@Data
public class CourierInf {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;

    @Column(name = "status")
    private String status;

    public CourierInf() {
    }

    public CourierInf(Users users, String status) {
        this.users = users;
        this.status = status;
    }
}
