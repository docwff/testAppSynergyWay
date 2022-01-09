package com.api.testappsynergyway.entity;


import com.api.testappsynergyway.entity.util.AbstractNameEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "FlightStatus")
public class FlightStatus extends AbstractNameEntity {

    public FlightStatus(Long id, String name) {
        super(id, name);
    }

    public FlightStatus(String name) {
        super(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FlightStatus that = (FlightStatus) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "FlightStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
