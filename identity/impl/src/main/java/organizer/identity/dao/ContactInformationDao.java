package organizer.identity.dao;

import organizer.PaginatedCollection;
import organizer.dao.BusinessObjectDao;
import organizer.mo.ModelObject;
import organizer.bo.AbstractBusinessObject;
import organizer.identity.mo.ContactInformationType;
import organizer.identity.mo.ContactType;

public interface ContactInformationDao<M extends ModelObject, B extends AbstractBusinessObject<M>> extends BusinessObjectDao<M, B> {

    PaginatedCollection<B> getByContact(ContactType contactType, long contactId, int offset, int limit);

    PaginatedCollection<B> getActiveByContact(ContactType contactType, long contactId, int offset, int limit);

    PaginatedCollection<B> getInactiveByContact(ContactType contactType, long contactId, int offset, int limit);

    B getPrimaryByContact(ContactType contactType, long contactId);

    B getByType(ContactType contactType, long contactId, ContactInformationType type);
}
