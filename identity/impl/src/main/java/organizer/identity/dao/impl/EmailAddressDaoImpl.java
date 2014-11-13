package organizer.identity.dao.impl;

import organizer.identity.bo.EmailAddressBo;
import organizer.identity.dao.EmailAddressDao;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.EmailAddress;
import organizer.identity.mo.EmailAddressType;
import org.springframework.stereotype.Repository;

@Repository
public class EmailAddressDaoImpl extends AbstractContactInformationDao<EmailAddress, EmailAddressBo> implements EmailAddressDao {

    public EmailAddressDaoImpl() {
        super(EmailAddressBo.class);
    }

    @Override
    public EmailAddressBo getByType(ContactType contactType, long contactId, EmailAddressType type) {
        return super.getByType(contactType, contactId, type);
    }
}
