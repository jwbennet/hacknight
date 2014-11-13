package organizer.identity.dao.impl;

import organizer.identity.mo.AddressType;
import organizer.identity.bo.AddressBo;
import organizer.identity.dao.AddressDao;
import organizer.identity.mo.Address;
import organizer.identity.mo.ContactType;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl extends AbstractContactInformationDao<Address, AddressBo> implements AddressDao {

    public AddressDaoImpl() {
        super(AddressBo.class);
    }

    @Override
    public AddressBo getByType(ContactType contactType, long contactId, AddressType type) {
        return super.getByType(contactType, contactId, type);
    }
}
