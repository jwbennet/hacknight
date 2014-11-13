package organizer.identity.dao;

import organizer.identity.bo.AddressBo;
import organizer.identity.mo.AddressType;
import organizer.identity.mo.Address;
import organizer.identity.mo.ContactType;

public interface AddressDao extends ContactInformationDao<Address, AddressBo> {

    AddressBo getByType(ContactType contactType, long contactId, AddressType type);
}
