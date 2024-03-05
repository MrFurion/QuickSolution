package by.trubetski.quickSolution.models;

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
    private User users;

    @Column(name = "status")
    private String status;

    public CourierInf() {
    }

    public CourierInf(User users, String status) {
        this.users = users;
        this.status = status;
    }
}
