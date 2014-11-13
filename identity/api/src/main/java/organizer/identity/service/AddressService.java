package organizer.identity.service;

import organizer.identity.mo.Address;
import organizer.identity.mo.AddressType;
import organizer.identity.mo.ContactType;

public interface AddressService extends ContactInformationService<Address> {

    Address getByType(ContactType contactType, Long contactId, AddressType type);
}
