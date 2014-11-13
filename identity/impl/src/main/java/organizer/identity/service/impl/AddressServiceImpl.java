package organizer.identity.service.impl;

import organizer.identity.mo.AddressType;
import organizer.identity.bo.AddressBo;
import organizer.identity.dao.AddressDao;
import organizer.identity.mo.Address;
import organizer.identity.mo.ContactType;
import organizer.identity.service.AbstractContactInformationService;
import organizer.identity.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "addressService")
public class AddressServiceImpl extends AbstractContactInformationService<Address, AddressBo> implements AddressService {

    @Autowired
    private AddressDao dao;

    public AddressServiceImpl() {
        super(AddressBo.class);
    }

    @Override
    public Address getByType(ContactType contactType, Long contactId, AddressType type) {
        return super.getByType(contactType, contactId, type);
    }

    @Override
    protected AddressBo prepareForCreate(Address mo) {
        return super.prepareForCreate(mo)
                .setType(mo.getType())
                .setAddressLine1(mo.getAddressLine1())
                .setAddressLine2(mo.getAddressLine2())
                .setAddressLine3(mo.getAddressLine3())
                .setCity(mo.getCity())
                .setState(mo.getState())
                .setPostalCode(mo.getPostalCode())
                .setCountry(mo.getCountry());
    }

    @Override
    protected AddressBo prepareForUpdate(AddressBo existingBo, Address mo) {
        return super.prepareForUpdate(existingBo, mo)
                .setType(mo.getType())
                .setAddressLine1(mo.getAddressLine1())
                .setAddressLine2(mo.getAddressLine2())
                .setAddressLine3(mo.getAddressLine3())
                .setCity(mo.getCity())
                .setState(mo.getState())
                .setPostalCode(mo.getPostalCode())
                .setCountry(mo.getCountry());
    }

    @Override
    public AddressDao getDao() {
        return dao;
    }

    public void setDao(AddressDao dao) {
        this.dao = dao;
    }
}
