package organizer.dao.impl;

import organizer.*;
import organizer.dao.BusinessObjectDao;
import organizer.mo.ModelObject;
import organizer.bo.AbstractBusinessObject;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Transactional(readOnly = true)
public class AbstractBusinessObjectDao<M extends ModelObject, B extends AbstractBusinessObject<M>> implements BusinessObjectDao<M, B> {

    private final Class<B> boClass;
    @PersistenceContext
    private EntityManager entityManager;

    public AbstractBusinessObjectDao(Class<B> boClass) {
        this.boClass = boClass;
    }

    @Override
    public B getById(long id) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<B> criteria = builder.createQuery(getBoClass());
        Root<B> from = criteria.from(getBoClass());
        criteria.select(from)
                .where(builder.equal(from.get("id"), id));
        TypedQuery<B> query = getEntityManager().createQuery(criteria);
        return query.getSingleResult();
    }

    @Override
    public PaginatedCollection<B> getAll() {
        return getAll(0, Constants.DEFAULT_RESULTS_LIMIT);
    }

    @Override
    public PaginatedCollection<B> getAll(int offset) {
        return getAll(offset, Constants.DEFAULT_RESULTS_LIMIT);
    }

    @Override
    public PaginatedCollection<B> getAll(int offset, int limit) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        countQuery.select(builder.count(countQuery.from(getBoClass())));
        Long count = getEntityManager().createQuery(countQuery).getSingleResult();

        CriteriaQuery<B> criteria = builder.createQuery(getBoClass());
        Root<B> from = criteria.from(getBoClass());
        CriteriaQuery<B> select = criteria.select(from);
        TypedQuery<B> query = getEntityManager().createQuery(select);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        PaginatedList<B> paginatedResult = new PaginatedArrayList<>(query.getResultList());
        paginatedResult.setCount(count).setLimit(limit).setOffset(offset);
        return paginatedResult;
    }

    @Override
    public PaginatedCollection<B> getActive() {
        return getActive(0, Constants.DEFAULT_RESULTS_LIMIT);
    }

    @Override
    public PaginatedCollection<B> getActive(int offset) {
        return getActive(offset, Constants.DEFAULT_RESULTS_LIMIT);
    }

    @Override
    public PaginatedCollection<B> getActive(int offset, int limit) {
        DateTime now = DateTime.now();
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
        Root<B> countFrom = countCriteria.from(getBoClass());
        countCriteria.select(builder.count(countFrom))
                .where(
                        builder.and(
                                builder.or(
                                        builder.isNull(countFrom.get("activeFromDate")),
                                        builder.lessThan(countFrom.get("activeFromDate"), now)
                                ),
                                builder.or(
                                        builder.isNull(countFrom.get("activeToDate")),
                                        builder.greaterThan(countFrom.get("activeToDate"), now)
                                )
                        )
                );
        Long count = getEntityManager().createQuery(countCriteria).getSingleResult();

        CriteriaQuery<B> criteria = builder.createQuery(getBoClass());
        Root<B> from = criteria.from(getBoClass());
        criteria.select(from)
                .where(
                        builder.and(
                                builder.or(
                                        builder.isNull(from.get("activeFromDate")),
                                        builder.lessThan(from.get("activeFromDate"), now)
                                ),
                                builder.or(
                                        builder.isNull(from.get("activeToDate")),
                                        builder.greaterThan(from.get("activeToDate"), now)
                                )
                        )
                );
        TypedQuery<B> query = getEntityManager().createQuery(criteria);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        PaginatedList<B> paginatedResult = new PaginatedArrayList<>(query.getResultList());
        paginatedResult.setCount(count).setLimit(limit).setOffset(offset);
        return paginatedResult;
    }

    @Override
    public PaginatedCollection<B> getInactive() {
        return getInactive(0, Constants.DEFAULT_RESULTS_LIMIT);
    }

    @Override
    public PaginatedCollection<B> getInactive(int offset) {
        return getInactive(offset, Constants.DEFAULT_RESULTS_LIMIT);
    }

    @Override
    public PaginatedCollection<B> getInactive(int offset, int limit) {
        DateTime now = DateTime.now();
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
        Root<B> countFrom = countCriteria.from(getBoClass());
        countCriteria.select(builder.count(countFrom))
                .where(
                        builder.or(
                                builder.and(
                                        builder.isNotNull(countFrom.get("activeFromDate")),
                                        builder.greaterThan(countFrom.get("activeFromDate"), now)
                                ),
                                builder.and(
                                        builder.isNotNull(countFrom.get("activeToDate")),
                                        builder.lessThan(countFrom.get("activeToDate"), now)
                                )
                        )
                );
        Long count = getEntityManager().createQuery(countCriteria).getSingleResult();

        CriteriaQuery<B> criteria = builder.createQuery(getBoClass());
        Root<B> from = criteria.from(getBoClass());
        criteria.select(from)
                .where(
                        builder.or(
                                builder.and(
                                        builder.isNotNull(from.get("activeFromDate")),
                                        builder.greaterThan(from.get("activeFromDate"), now)
                                ),
                                builder.and(
                                        builder.isNotNull(from.get("activeToDate")),
                                        builder.lessThan(from.get("activeToDate"), now)
                                )
                        )
                );
        TypedQuery<B> query = getEntityManager().createQuery(criteria);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        PaginatedList<B> paginatedResult = new PaginatedArrayList<>(query.getResultList());
        paginatedResult.setCount(count).setLimit(limit).setOffset(offset);
        return paginatedResult;
    }

    @Override
    @Transactional(readOnly = false)
    public B save(B businessObject) {
        if( businessObject.getId() == null ) {
            getEntityManager().persist(businessObject);
        } else {
            businessObject = getEntityManager().merge(businessObject);
        }
        getEntityManager().flush();
        return businessObject;
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(long id) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaDelete<B> deleteCriteria = builder.createCriteriaDelete(getBoClass());
        Root<B> from = deleteCriteria.from(getBoClass());
        deleteCriteria.where(
                builder.equal(from.get("id"), id)
        );
        getEntityManager().createQuery(deleteCriteria).executeUpdate();
    }

    @Override
    public PaginatedList<B> search(SearchCriteria criteria) {
        return null;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected Class<B> getBoClass() {
        return boClass;
    }
}
