package organizer.identity.service;

import organizer.identity.mo.ContactType;
import organizer.identity.mo.PhoneNumber;
import organizer.identity.mo.PhoneNumberType;

public interface PhoneNumberService extends ContactInformationService<PhoneNumber> {

    PhoneNumber getByType(ContactType contactType, Long contactId, PhoneNumberType type);
}
