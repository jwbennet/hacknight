package organizer.identity.service.impl;

import organizer.identity.bo.EmailAddressBo;
import organizer.identity.mo.EmailAddress;
import organizer.identity.service.EmailAddressService;
import organizer.identity.dao.EmailAddressDao;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.EmailAddressType;
import organizer.identity.service.AbstractContactInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "emailAddressService")
public class EmailAddressServiceImpl extends AbstractContactInformationService<EmailAddress, EmailAddressBo> implements EmailAddressService {

    @Autowired
    private EmailAddressDao dao;

    public EmailAddressServiceImpl() {
        super(EmailAddressBo.class);
    }

    @Override
    public EmailAddress getByType(ContactType contactType, Long contactId, EmailAddressType type) {
        return super.getByType(contactType, contactId, type);
    }

    @Override
    protected EmailAddressBo prepareForCreate(EmailAddress mo) {
        return super.prepareForCreate(mo)
                .setType(mo.getType())
                .setEmailAddress(mo.getEmailAddress());
    }

    @Override
    protected EmailAddressBo prepareForUpdate(EmailAddressBo existingBo, EmailAddress mo) {
        return super.prepareForUpdate(existingBo, mo)
                .setType(mo.getType())
                .setEmailAddress(mo.getEmailAddress());
    }

    @Override
    public EmailAddressDao getDao() {
        return dao;
    }

    public void setDao(EmailAddressDao dao) {
        this.dao = dao;
    }
}
