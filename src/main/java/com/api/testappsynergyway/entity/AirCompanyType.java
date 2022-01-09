package com.api.testappsynergyway.entity;

import com.api.testappsynergyway.entity.util.AbstractNameEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

import static java.util.Objects.hash;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "AirCompanyType")
public class AirCompanyType extends AbstractNameEntity {

    public AirCompanyType(String name) {
        super(name);
    }

    public AirCompanyType(Long id, String name) {
        super(id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirCompanyType airCompanyType = (AirCompanyType) o;
        return Objects.equals(id, airCompanyType.id);
    }

    @Override
    public int hashCode() {
        return hash(id);
    }

    @Override
    public String toString() {
        return "AirCompanyType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
