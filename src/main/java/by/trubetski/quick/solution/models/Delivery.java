package by.trubetski.quick.solution.models;



import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.generator.Generator;
import org.locationtech.jts.geom.Point;


import java.io.Serializable;

import static org.hibernate.annotations.UuidGenerator.*;


@Entity
@Table(name = "Delivery")
@Data
public class Delivery implements Serializable {
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


    @Column(name = "start_coordinates")
    private Point coordinatesStart;
    @Column(name = "finish_coordinates")
    private Point coordinatesFinish;


    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orders;
    @OneToOne(mappedBy = "del")
    private Orders ord;

    public Delivery() {
    }

    public Delivery(String startAddress, String finishAddress) {
        this.startAddress = startAddress;
        this.finishAddress = finishAddress;
    }
}
