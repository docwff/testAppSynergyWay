package com.api.testappsynergyway.dao.util;

import com.api.testappsynergyway.entity.util.AbstractEntity;
import com.sun.istack.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface AbstractDao<OBJ extends AbstractEntity, ID extends Serializable> {

    OBJ saveOrUpdate(@NotNull OBJ entity);
    void saveAll(List<OBJ> entityList);
    void flush();
    List<OBJ> findAll();
    Page<OBJ> findAll(Pageable pageable);
    @NotNull Optional<OBJ> findById(@NotNull ID entityId);
    OBJ findByIdExpected(@NotNull ID entityId);
    void delete(@NotNull OBJ entity);
    void deleteById(@NotNull ID entityId);
    void deleteAll();


}