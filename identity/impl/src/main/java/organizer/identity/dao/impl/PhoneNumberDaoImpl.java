package organizer.identity.dao.impl;

import organizer.identity.bo.PhoneNumberBo;
import organizer.identity.dao.PhoneNumberDao;
import organizer.identity.mo.PhoneNumberType;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.PhoneNumber;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneNumberDaoImpl extends AbstractContactInformationDao<PhoneNumber, PhoneNumberBo> implements PhoneNumberDao {

    public PhoneNumberDaoImpl() {
        super(PhoneNumberBo.class);
    }

    @Override
    public PhoneNumberBo getByType(ContactType contactType, long contactId, PhoneNumberType type) {
        return super.getByType(contactType, contactId, type);
    }
}
