package com.api.testappsynergyway.entity;

import com.api.testappsynergyway.entity.util.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.util.Objects.hash;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.FetchType.EAGER;
import static lombok.AccessLevel.PRIVATE;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "AirCompany")
public class AirCompany extends AbstractEntity {

    @Column(name = "name", nullable = false)
    String name;

    @ManyToOne(cascade = MERGE, fetch = EAGER)
    @JoinColumn(name = "airCompanyTypeId", nullable = false)
    AirCompanyType airCompanyType;

    @Column(name = "foundedAt", nullable = false)
    @CreationTimestamp
    LocalDate foundedAt;

    public AirCompany(Long id, String name, AirCompanyType airCompanyType, LocalDate foundedAt) {
        super(id);
        this.name = name;
        this.airCompanyType = airCompanyType;
        this.foundedAt = foundedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirCompany airCompany = (AirCompany) o;
        return Objects.equals(id, airCompany.id);
    }

    @Override
    public int hashCode() {
        return hash(id);
    }

    @Override
    public String toString() {
        return "AirCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", airCompanyType=" + airCompanyType +
                ", foundedAt=" + foundedAt +
                '}';
    }
}
