package organizer.identity.service.impl;

import organizer.identity.service.AbstractContactInformationService;
import organizer.identity.bo.NameBo;
import organizer.identity.dao.NameDao;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.Name;
import organizer.identity.mo.NameType;
import organizer.identity.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "nameService")
public class NameServiceImpl extends AbstractContactInformationService<Name, NameBo> implements NameService {

    @Autowired
    private NameDao dao;

    public NameServiceImpl() {
        super(NameBo.class);
    }

    @Override
    public Name getByType(ContactType contactType, Long contactId, NameType type) {
        return super.getByType(contactType, contactId, type);
    }

    @Override
    protected NameBo prepareForCreate(Name mo) {
        return super.prepareForCreate(mo)
                .setType(mo.getType())
                .setGivenName(mo.getGivenName())
                .setMiddleName(mo.getMiddleName())
                .setSurname(mo.getSurname())
                .setSuffix(mo.getSuffix());
    }

    @Override
    protected NameBo prepareForUpdate(NameBo existingBo, Name mo) {
        return super.prepareForUpdate(existingBo, mo)
                .setType(mo.getType())
                .setGivenName(mo.getGivenName())
                .setMiddleName(mo.getMiddleName())
                .setSurname(mo.getSurname())
                .setSuffix(mo.getSuffix());
    }

    @Override
    public NameDao getDao() {
        return dao;
    }

    public void setDao(NameDao dao) {
        this.dao = dao;
    }
}
