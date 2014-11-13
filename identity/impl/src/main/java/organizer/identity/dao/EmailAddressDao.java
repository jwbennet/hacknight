package organizer.identity.dao;

import organizer.identity.bo.EmailAddressBo;
import organizer.identity.mo.EmailAddress;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.EmailAddressType;

public interface EmailAddressDao extends ContactInformationDao<EmailAddress, EmailAddressBo> {

    EmailAddressBo getByType(ContactType contactType, long contactId, EmailAddressType type);
}
