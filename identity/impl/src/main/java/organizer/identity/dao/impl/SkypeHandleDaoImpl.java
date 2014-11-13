package organizer.identity.dao.impl;

import organizer.identity.bo.SkypeHandleBo;
import organizer.identity.dao.SkypeHandleDao;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.SkypeHandle;
import organizer.identity.mo.SkypeHandleType;
import org.springframework.stereotype.Repository;

@Repository
public class SkypeHandleDaoImpl extends AbstractContactInformationDao<SkypeHandle, SkypeHandleBo> implements SkypeHandleDao {

    public SkypeHandleDaoImpl() {
        super(SkypeHandleBo.class);
    }

    @Override
    public SkypeHandleBo getByType(ContactType contactType, long contactId, SkypeHandleType type) {
        return super.getByType(contactType, contactId, type);
    }
}
