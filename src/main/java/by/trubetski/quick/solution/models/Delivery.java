package by.trubetski.quick.solution.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Delivery")
@Data
public class Delivery {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "start_address")
    private String startAddress;
    @Column(name = "finish_address")
    private String finishAddress;
    @Column(name = "courier_id")
    private int courierId;
    @Column(name = "status")
    private String status;
    @Column(name = "coordinates")
    private long coordinates;
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orders;
    @OneToOne(mappedBy = "del")
    private Orders ord;


}
