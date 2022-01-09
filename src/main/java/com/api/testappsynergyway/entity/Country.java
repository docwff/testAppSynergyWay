package com.api.testappsynergyway.entity;

import com.api.testappsynergyway.entity.util.AbstractEntity;
import com.api.testappsynergyway.entity.util.AbstractNameEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
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
@Table(name = "Country")
public class Country extends AbstractNameEntity {

    @Column(name = "iso")
    String iso;
    @Column(name = "iso3")
    String iso3;

    public Country(String name, String iso, String iso3) {
        super(name);
        this.iso = iso;
        this.iso3 = iso3;
    }

    public Country(Long id, String name, String iso, String iso3) {
        super(id, name);
        this.iso = iso;
        this.iso3 = iso3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(id, country.id);
    }

    @Override
    public int hashCode() {
        return hash(id);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", iso='" + iso + '\'' +
                ", iso3='" + iso3 + '\'' +
                '}';
    }

}
