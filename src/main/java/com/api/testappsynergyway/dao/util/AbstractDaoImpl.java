package com.api.testappsynergyway.dao.util;

import com.api.testappsynergyway.entity.util.AbstractEntity;
import net.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public abstract class AbstractDaoImpl<OBJ extends AbstractEntity, ID extends Serializable>
        implements AbstractDao<OBJ, ID> {

    protected final AbstractRepository<OBJ, ID> abstractRepository;

    public AbstractDaoImpl(AbstractRepository<OBJ, ID> abstractRepository) {
        this.abstractRepository = abstractRepository;
    }

    @SuppressWarnings("unchecked")
    private String getEntityName() {
        return ((Class<OBJ>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]).getSimpleName();
    }

    @Override
    public OBJ saveOrUpdate(OBJ entity) {
        return abstractRepository.save(entity);
    }

    @Override
    public void saveAll(List<OBJ> entityList) {
        abstractRepository.saveAll(entityList);
    }

    @Override
    public Page<OBJ> findAll(Pageable pageable) {
        return abstractRepository.findAll(pageable);
    }

    @Override
    public void flush() {
        abstractRepository.flush();
    }

    @Override
    public List<OBJ> findAll() {
        return abstractRepository.findAll();
    }

    @Override
    public Optional<OBJ> findById(ID entityId) {
        return abstractRepository.findById(entityId);
    }

    @Override
    public OBJ findByIdExpected(ID entityId) {
        return abstractRepository.findById(entityId)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                String.format("Entity %s with id %s not found", getEntityName(), entityId)));
    }

    @Override
    public void delete(OBJ entity) {
        abstractRepository.delete(entity);
    }

    @Override
    public void deleteById(ID entityId) {
        abstractRepository.deleteById(entityId);
    }

    @Override
    public void deleteAll() {
        abstractRepository.deleteAll();
    }

}