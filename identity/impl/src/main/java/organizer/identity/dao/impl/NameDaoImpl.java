package organizer.identity.dao.impl;

import organizer.identity.bo.NameBo;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.Name;
import organizer.identity.mo.NameType;
import organizer.identity.dao.NameDao;
import org.springframework.stereotype.Repository;

@Repository
public class NameDaoImpl extends AbstractContactInformationDao<Name, NameBo> implements NameDao {

    public NameDaoImpl() {
        super(NameBo.class);
    }

    @Override
    public NameBo getByType(ContactType contactType, long contactId, NameType type) {
        return super.getByType(contactType, contactId, type);
    }
}
