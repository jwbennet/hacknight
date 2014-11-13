package organizer.identity.service.impl;

import organizer.identity.bo.PhoneNumberBo;
import organizer.identity.dao.PhoneNumberDao;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.PhoneNumber;
import organizer.identity.mo.PhoneNumberType;
import organizer.identity.service.AbstractContactInformationService;
import organizer.identity.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "phoneNumberService")
public class PhoneNumberServiceImpl extends AbstractContactInformationService<PhoneNumber, PhoneNumberBo> implements PhoneNumberService {

    @Autowired
    private PhoneNumberDao dao;

    public PhoneNumberServiceImpl() {
        super(PhoneNumberBo.class);
    }

    @Override
    public PhoneNumber getByType(ContactType contactType, Long contactId, PhoneNumberType type) {
        return super.getByType(contactType, contactId, type);
    }

    @Override
    protected PhoneNumberBo prepareForCreate(PhoneNumber mo) {
        return super.prepareForCreate(mo)
                .setType(mo.getType())
                .setPhoneNumber(mo.getPhoneNumber())
                .setExtension(mo.getExtension());
    }

    @Override
    protected PhoneNumberBo prepareForUpdate(PhoneNumberBo existingBo, PhoneNumber mo) {
        return super.prepareForUpdate(existingBo, mo)
                .setType(mo.getType())
                .setPhoneNumber(mo.getPhoneNumber())
                .setExtension(mo.getExtension());
    }

    @Override
    public PhoneNumberDao getDao() {
        return dao;
    }

    public void setDao(PhoneNumberDao dao) {
        this.dao = dao;
    }
}
