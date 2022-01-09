package com.api.testappsynergyway.dao.util;

import com.api.testappsynergyway.entity.util.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface AbstractRepository<OBJ extends AbstractEntity, ID extends Serializable>
        extends JpaRepository<OBJ, ID> {

}