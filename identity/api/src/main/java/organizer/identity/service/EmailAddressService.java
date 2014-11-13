package organizer.identity.service;

import organizer.identity.mo.ContactType;
import organizer.identity.mo.EmailAddress;
import organizer.identity.mo.EmailAddressType;

public interface EmailAddressService extends ContactInformationService<EmailAddress> {

    EmailAddress getByType(ContactType contactType, Long contactId, EmailAddressType type);
}
