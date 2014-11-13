package organizer.identity.dao.impl;

import organizer.PaginatedArrayList;
import organizer.PaginatedCollection;
import organizer.PaginatedList;
import organizer.dao.impl.AbstractBusinessObjectDao;
import organizer.identity.dao.ContactInformationDao;
import organizer.mo.ModelObject;
import organizer.bo.AbstractBusinessObject;
import organizer.identity.mo.ContactInformationType;
import organizer.identity.mo.ContactType;
import org.joda.time.DateTime;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractContactInformationDao<M extends ModelObject, B extends AbstractBusinessObject<M>> extends AbstractBusinessObjectDao<M, B> implements ContactInformationDao<M, B> {

    public AbstractContactInformationDao(Class<B> boClass) {
        super(boClass);
    }

    @Override
    public PaginatedCollection<B> getByContact(ContactType contactType, long contactId, int offset, int limit) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
        Root<B> countFrom = countCriteria.from(getBoClass());
        countCriteria.select(builder.count(countFrom))
                .where(builder.and(
                        builder.equal(countFrom.get("contactType"), contactType),
                        builder.equal(countFrom.get("contactId"), contactId)
                ));
        Long count = getEntityManager().createQuery(countCriteria).getSingleResult();

        CriteriaQuery<B> criteria = builder.createQuery(getBoClass());
        Root<B> from = criteria.from(getBoClass());
        criteria.select(from)
                .where(builder.and(
                        builder.equal(from.get("contactType"), contactType),
                        builder.equal(from.get("contactId"), contactId)
                ));
        TypedQuery<B> query = getEntityManager().createQuery(criteria);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        PaginatedList<B> paginatedResult = new PaginatedArrayList<>(query.getResultList());
        paginatedResult.setCount(count).setLimit(limit).setOffset(offset);
        return paginatedResult;
    }

    @Override
    public PaginatedCollection<B> getActiveByContact(ContactType contactType, long contactId, int offset, int limit) {
        DateTime now = DateTime.now();
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
        Root<B> countFrom = countCriteria.from(getBoClass());
        countCriteria.select(builder.count(countFrom))
                .where(builder.and(
                        builder.equal(countFrom.get("contactType"), contactType),
                        builder.equal(countFrom.get("contactId"), contactId),
                        builder.or(
                                builder.isNull(countFrom.get("activeFromDate")),
                                builder.lessThan(countFrom.get("activeFromDate"), now)
                        ),
                        builder.or(
                                builder.isNull(countFrom.get("activeToDate")),
                                builder.greaterThan(countFrom.get("activeToDate"), now)
                        )
                ));
        Long count = getEntityManager().createQuery(countCriteria).getSingleResult();

        CriteriaQuery<B> criteria = builder.createQuery(getBoClass());
        Root<B> from = criteria.from(getBoClass());
        criteria.select(from)
                .where(builder.and(
                        builder.equal(from.get("contactType"), contactType),
                        builder.equal(from.get("contactId"), contactId),
                        builder.or(
                                builder.isNull(from.get("activeFromDate")),
                                builder.lessThan(from.get("activeFromDate"), now)
                        ),
                        builder.or(
                                builder.isNull(from.get("activeToDate")),
                                builder.greaterThan(from.get("activeToDate"), now)
                        )
                ));
        TypedQuery<B> query = getEntityManager().createQuery(criteria);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        PaginatedList<B> paginatedResult = new PaginatedArrayList<>(query.getResultList());
        paginatedResult.setCount(count).setLimit(limit).setOffset(offset);
        return paginatedResult;
    }

    @Override
    public PaginatedCollection<B> getInactiveByContact(ContactType contactType, long contactId, int offset, int limit) {
        DateTime now = DateTime.now();
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
        Root<B> countFrom = countCriteria.from(getBoClass());
        countCriteria.select(builder.count(countFrom))
                .where(builder.and(
                        builder.equal(countFrom.get("contactType"), contactType),
                        builder.equal(countFrom.get("contactId"), contactId),
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
                ));
        Long count = getEntityManager().createQuery(countCriteria).getSingleResult();

        CriteriaQuery<B> criteria = builder.createQuery(getBoClass());
        Root<B> from = criteria.from(getBoClass());
        criteria.select(from)
                .where(builder.and(
                        builder.equal(from.get("contactType"), contactType),
                        builder.equal(from.get("contactId"), contactId),
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
                ));
        TypedQuery<B> query = getEntityManager().createQuery(criteria);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        PaginatedList<B> paginatedResult = new PaginatedArrayList<>(query.getResultList());
        paginatedResult.setCount(count).setLimit(limit).setOffset(offset);
        return paginatedResult;
    }

    @Override
    public B getPrimaryByContact(ContactType contactType, long contactId) {
        DateTime now = DateTime.now();
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<B> criteria = builder.createQuery(getBoClass());
        Root<B> from = criteria.from(getBoClass());
        criteria.select(from)
                .where(builder.and(
                        builder.equal(from.get("contactType"), contactType),
                        builder.equal(from.get("contactId"), contactId),
                        builder.equal(from.get("primary"), true),
                        builder.or(
                                builder.isNull(from.get("activeFromDate")),
                                builder.lessThan(from.get("activeFromDate"), now)
                        ),
                        builder.or(
                                builder.isNull(from.get("activeToDate")),
                                builder.greaterThan(from.get("activeToDate"), now)
                        )
                ));
        TypedQuery<B> query = getEntityManager().createQuery(criteria);
        return query.getSingleResult();
    }

    @Override
    public B getByType(ContactType contactType, long contactId, ContactInformationType type) {
        DateTime now = DateTime.now();
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<B> criteria = builder.createQuery(getBoClass());
        Root<B> from = criteria.from(getBoClass());
        criteria.select(from)
                .where(builder.and(
                        builder.equal(from.get("contactType"), contactType),
                        builder.equal(from.get("contactId"), contactId),
                        builder.equal(from.get("type"), type),
                        builder.or(
                                builder.isNull(from.get("activeFromDate")),
                                builder.lessThan(from.get("activeFromDate"), now)
                        ),
                        builder.or(
                                builder.isNull(from.get("activeToDate")),
                                builder.greaterThan(from.get("activeToDate"), now)
                        )
                ));
        TypedQuery<B> query = getEntityManager().createQuery(criteria);
        return query.getSingleResult();
    }
}
