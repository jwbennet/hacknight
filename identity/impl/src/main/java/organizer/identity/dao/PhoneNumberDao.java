package organizer.identity.dao;

import organizer.identity.bo.PhoneNumberBo;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.PhoneNumber;
import organizer.identity.mo.PhoneNumberType;

public interface PhoneNumberDao extends ContactInformationDao<PhoneNumber, PhoneNumberBo> {

    PhoneNumberBo getByType(ContactType contactType, long contactId, PhoneNumberType type);
}
