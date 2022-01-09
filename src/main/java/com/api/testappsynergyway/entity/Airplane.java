package com.api.testappsynergyway.entity;

import com.api.testappsynergyway.entity.util.AbstractEntity;
import com.api.testappsynergyway.entity.util.AbstractNameEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

import static java.util.Objects.hash;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.FetchType.EAGER;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "Airplane")
public class Airplane extends AbstractNameEntity {

    @ManyToOne(cascade = MERGE, fetch = EAGER)
    @JoinColumn(name = "factoryId", nullable = false)
    Country factory;

    @Column(name = "serialNumber", nullable = false)
    String serialNumber;

    @ManyToOne(cascade = MERGE, fetch = EAGER)
    @JoinColumn(name = "airCompanyId")
    AirCompany company;

    @Column(name = "numberOfFlights", nullable = false)
    Integer numberOfFlights;

    @Column(name = "flightDistance", nullable = false)
    Integer flightDistance;

    @Column(name = "fuelCapacity", nullable = false)
    Integer fuelCapacity;

    @ManyToOne(cascade = MERGE, fetch = EAGER)
    @JoinColumn(name = "airplaneTypeId", nullable = false)
    AirplaneType type;

    @Column(name = "createdAt", nullable = false)
    @CreationTimestamp
    LocalDate createdAt;

    public Airplane(String name, Country factory, String serialNumber, AirCompany company,
                    Integer numberOfFlights, Integer flightDistance, Integer fuelCapacity,
                    AirplaneType type, LocalDate createdAt) {
        super(name);
        this.factory = factory;
        this.serialNumber = serialNumber;
        this.company = company;
        this.numberOfFlights = numberOfFlights;
        this.flightDistance = flightDistance;
        this.fuelCapacity = fuelCapacity;
        this.type = type;
        this.createdAt = createdAt;
    }

    public Airplane(Long id, String name, Country factory, String serialNumber,
                    AirCompany company, Integer numberOfFlights, Integer flightDistance, Integer fuelCapacity,
                    AirplaneType type, LocalDate createdAt) {
        super(id, name);
        this.factory = factory;
        this.serialNumber = serialNumber;
        this.company = company;
        this.numberOfFlights = numberOfFlights;
        this.flightDistance = flightDistance;
        this.fuelCapacity = fuelCapacity;
        this.type = type;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return Objects.equals(id, airplane.id);
    }

    @Override
    public int hashCode() {
        return hash(id);
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", factory='" + factory + '\'' +
                ", serialNumber=" + serialNumber +
                ", company=" + company +
                ", numberOfFlights=" + numberOfFlights +
                ", flightDistance=" + flightDistance +
                ", fuelCapacity=" + fuelCapacity +
                ", type='" + type + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
