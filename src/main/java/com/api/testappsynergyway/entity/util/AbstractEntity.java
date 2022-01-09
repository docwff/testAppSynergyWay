package com.api.testappsynergyway.entity.util;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import static java.util.Objects.hash;
import static javax.persistence.GenerationType.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractEntity implements Serializable {

    public AbstractEntity(Long id) {
        this.id = id;
    }

    @Id
    @JsonProperty(
            value = "id",
            required = true,
            index = 0)
    @Column(
            nullable = false,
            unique = true,
            insertable = false,
            updatable = false)
    @GeneratedValue(strategy = SEQUENCE)
    protected Long id;

}
