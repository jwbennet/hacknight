package organizer.identity.service;

import organizer.PaginatedCollection;
import organizer.identity.dao.ContactInformationDao;
import organizer.identity.mo.ContactInformation;
import organizer.service.impl.AbstractModelObjectService;
import organizer.identity.bo.AbstractContactInformationBo;
import organizer.identity.mo.ContactInformationType;
import organizer.identity.mo.ContactType;

public abstract class AbstractContactInformationService<M extends ContactInformation, B extends AbstractContactInformationBo<M>> extends AbstractModelObjectService<M, B> implements ContactInformationService<M> {

    protected AbstractContactInformationService(Class<B> boClass) {
        super(boClass);
    }

    public abstract ContactInformationDao<M, B> getDao();

    @Override
    public PaginatedCollection<M> getByContact(ContactType contactType, Long contactId, int offset, int limit) {
        return convertCollection(getDao().getByContact(contactType, contactId, offset, limit));
    }

    @Override
    public PaginatedCollection<M> getActiveByContact(ContactType contactType, Long contactId, int offset, int limit) {
        return convertCollection(getDao().getActiveByContact(contactType, contactId, offset, limit));
    }

    @Override
    public PaginatedCollection<M> getInactiveByContact(ContactType contactType, Long contactId, int offset, int limit) {
        return convertCollection(getDao().getInactiveByContact(contactType, contactId, offset, limit));
    }

    @Override
    public M getPrimaryByContact(ContactType contactType, Long contactId) {
        return getDao().getPrimaryByContact(contactType, contactId).asImmutable();
    }

    @Override
    public M getByType(ContactType contactType, Long contactId, ContactInformationType type) {
        return getDao().getByType(contactType, contactId, type).asImmutable();
    }

    @Override
    protected B prepareForCreate(M mo) {
        B bo = super.prepareForCreate(mo);
        bo.setPrimary(mo.isPrimary());
        bo.setContactType(mo.getContactType());
        bo.setContactId(mo.getContactId());
        return bo;
    }

    @Override
    protected B prepareForUpdate(B existingBo, M mo) {
        B bo = super.prepareForUpdate(existingBo, mo);
        bo.setPrimary(mo.isPrimary());
        bo.setContactType(mo.getContactType());
        bo.setContactId(mo.getContactId());
        return bo;
    }
}
