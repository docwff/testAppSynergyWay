package com.api.testappsynergyway.entity.util;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractNameEntity extends AbstractEntity {

    public AbstractNameEntity() {
    }

    public AbstractNameEntity(String name) {
        this.name = name;
    }

    public AbstractNameEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Column(name = "name")
    protected String name;


}
