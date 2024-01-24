package by.trubetski.QuickSolution.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "courierInf")
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

}
