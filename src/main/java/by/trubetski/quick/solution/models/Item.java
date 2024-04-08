package by.trubetski.quick.solution.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orders;
    @Column(name = "type_orders")
    private String typeOrder;
    @Column(name = "type_delivery")
    private String typeDelivery;
}
