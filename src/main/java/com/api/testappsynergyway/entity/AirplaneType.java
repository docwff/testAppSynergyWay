package com.api.testappsynergyway.entity;


import com.api.testappsynergyway.entity.util.AbstractNameEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "AirplaneType")
public class AirplaneType extends AbstractNameEntity {

    public AirplaneType(String name) {
        super(name);
    }

    public AirplaneType(Long id, String name) {
        super(id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirplaneType that = (AirplaneType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AirplaneType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
