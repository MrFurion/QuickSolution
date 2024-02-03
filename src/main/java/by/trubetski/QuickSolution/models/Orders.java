package by.trubetski.QuickSolution.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "Orders")
@Data
public class Orders {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User owner;
    @OneToOne(mappedBy = "orders")
    private Delivery delivery;
    @OneToOne
    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
    private Delivery del;


    public Orders() {
    }

    public Orders(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }
}
