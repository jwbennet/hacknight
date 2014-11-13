package organizer.service.impl;

import organizer.ImmutablePaginatedArrayList;
import organizer.PaginatedCollection;
import organizer.PaginatedList;
import organizer.SearchCriteria;
import organizer.bo.AbstractBusinessObject;
import organizer.dao.BusinessObjectDao;
import organizer.mo.ModelObject;
import organizer.service.ModelObjectService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public abstract class AbstractModelObjectService<M extends ModelObject, B extends AbstractBusinessObject<M>> implements ModelObjectService<M> {

    private final Class<B> boClass;

    protected AbstractModelObjectService(Class<B> boClass) {
        this.boClass = boClass;
    }

    protected abstract BusinessObjectDao<M, B> getDao();

    private B getByIdInternal(long id) {
        return getDao().getById(id);
    }

    @Override
    public M getById(long id) {
        B businessObject = getByIdInternal(id);
        return businessObject.asImmutable();
    }

    @Override
    public PaginatedCollection<M> getAll() {
        return convertCollection(getDao().getAll());
    }

    @Override
    public PaginatedCollection<M> getAll(int offset) {
        return convertCollection(getDao().getAll(offset));
    }

    @Override
    public PaginatedCollection<M> getAll(int offset, int limit) {
        return convertCollection(getDao().getAll(offset, limit));
    }

    @Override
    public PaginatedCollection<M> getActive() {
        return convertCollection(getDao().getActive());
    }

    @Override
    public PaginatedCollection<M> getActive(int offset) {
        return convertCollection(getDao().getActive(offset));
    }

    @Override
    public PaginatedCollection<M> getActive(int offset, int limit) {
        return convertCollection(getDao().getActive(offset, limit));
    }

    @Override
    public PaginatedCollection<M> getInactive() {
        return convertCollection(getDao().getInactive());
    }

    @Override
    public PaginatedCollection<M> getInactive(int offset) {
        return convertCollection(getDao().getInactive(offset));
    }

    @Override
    public PaginatedCollection<M> getInactive(int offset, int limit) {
        return convertCollection(getDao().getInactive(offset, limit));
    }

    @Override
    public M create(M modelObject) {
        return getDao().save(prepareForCreate(modelObject)).asImmutable();
    }

    @Override
    public M update(Long id, M modelObject) {
        B existingBo = getByIdInternal(id);
        return getDao().save(prepareForUpdate(existingBo, modelObject)).asImmutable();
    }

    @Override
    public void delete(Long id) {
        getDao().delete(id);
    }

    @Override
    public PaginatedList<M> search(SearchCriteria criteria) {
        return null;
    }

    protected B prepareForCreate(M mo) {
        try {
            B bo = getBoClass().newInstance();
            bo.setTenantId(mo.getTenantId());
            bo.setActiveFromDate(mo.getActiveFromDate());
            bo.setActiveToDate(mo.getActiveToDate());
            return bo;
        } catch ( InstantiationException | IllegalAccessException e ) {
            throw new IllegalStateException("Unable to create new instance of " + boClass.getName(), e);
        }
    }

    protected B prepareForUpdate(B existingBo, M mo) {
        existingBo.setActiveFromDate(mo.getActiveFromDate());
        existingBo.setActiveToDate(mo.getActiveToDate());
        return existingBo;
    }

    public Class<B> getBoClass() {
        return boClass;
    }

    protected PaginatedCollection<M> convertCollection(PaginatedCollection<B> businessObjects) {
        Collection<M> modelObjects = new ArrayList<>();
        businessObjects.stream().forEach(bo -> modelObjects.add(bo.asImmutable()));
        return new ImmutablePaginatedArrayList<>(Collections.unmodifiableCollection(modelObjects), businessObjects.getCount(), businessObjects.getOffset(), businessObjects.getLimit());
    }
}
