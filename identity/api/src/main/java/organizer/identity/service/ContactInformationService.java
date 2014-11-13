package organizer.identity.service;

import organizer.PaginatedCollection;
import organizer.identity.mo.ContactInformationType;
import organizer.identity.mo.ContactType;
import organizer.mo.ModelObject;
import organizer.service.ModelObjectService;

public interface ContactInformationService<M extends ModelObject> extends ModelObjectService<M> {

    PaginatedCollection<M> getByContact(ContactType contactType, Long contactId, int offset, int limit);

    PaginatedCollection<M> getActiveByContact(ContactType contactType, Long contactId, int offset, int limit);

    PaginatedCollection<M> getInactiveByContact(ContactType contactType, Long contactId, int offset, int limit);

    M getPrimaryByContact(ContactType contactType, Long contactId);

    M getByType(ContactType contactType, Long contactId, ContactInformationType type);

}
