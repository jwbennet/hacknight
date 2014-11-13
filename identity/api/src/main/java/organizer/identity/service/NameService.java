package organizer.identity.service;

import organizer.identity.mo.ContactType;
import organizer.identity.mo.Name;
import organizer.identity.mo.NameType;

public interface NameService extends ContactInformationService<Name> {

    Name getByType(ContactType contactType, Long contactId, NameType type);
}
