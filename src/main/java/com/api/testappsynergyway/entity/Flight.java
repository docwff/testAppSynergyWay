package com.api.testappsynergyway.entity;

import com.api.testappsynergyway.entity.util.AbstractEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.FetchType.EAGER;
import static lombok.AccessLevel.PRIVATE;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "Flight")
public class Flight extends AbstractEntity {

    @ManyToOne(cascade = MERGE, fetch = EAGER)
    @JoinColumn(name = "flightStatusId", nullable = false)
    FlightStatus status;

    @ManyToOne(cascade = MERGE, fetch = EAGER)
    @JoinColumn(name = "airplaneId", nullable = false)
    Airplane airplane;

    @ManyToOne(cascade = MERGE, fetch = EAGER)
    @JoinColumn(name = "departureCountryId", nullable = false)
    Country departureCountry;

    @ManyToOne(cascade = MERGE, fetch = EAGER)
    @JoinColumn(name = "destinationCountryId", nullable = false)
    Country destinationCountry;

    @Column(name = "distance", nullable = false)
    Integer distance;

    @Column(name = "estimatedFlightTime", nullable = false)
    LocalTime estimatedFlightTime;

    @Column(name = "departure")
    LocalDateTime departure;

    @Column(name = "arrived")
    LocalDateTime arrived;

    @Column(name = "delayStartedAt")
    LocalDateTime delayStartedAt;

    @Column(name = "createdAt", nullable = false)
    @CreationTimestamp
    Timestamp createdAt;

    public Flight(Long id, FlightStatus status, Airplane airplane,
                  Country departureCountry, Country destinationCountry, Integer distance,
                  LocalTime estimatedFlightTime, LocalDateTime departure, LocalDateTime arrived,
                  LocalDateTime delayStartedAt, Timestamp createdAt) {
        super(id);
        this.status = status;
        this.airplane = airplane;
        this.departureCountry = departureCountry;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
        this.estimatedFlightTime = estimatedFlightTime;
        this.departure = departure;
        this.arrived = arrived;
        this.delayStartedAt = delayStartedAt;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", status=" + status +
                ", airplane=" + airplane +
                ", departureCountry=" + departureCountry +
                ", destinationCountry=" + destinationCountry +
                ", distance=" + distance +
                ", estimatedFlightTime=" + estimatedFlightTime +
                ", departure=" + departure +
                ", arrived=" + arrived +
                ", delayStartedAt=" + delayStartedAt +
                ", createdAt=" + createdAt +
                '}';
    }
}

