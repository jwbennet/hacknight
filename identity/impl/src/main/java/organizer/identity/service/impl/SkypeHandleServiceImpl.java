package organizer.identity.service.impl;

import organizer.identity.bo.SkypeHandleBo;
import organizer.identity.dao.SkypeHandleDao;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.SkypeHandle;
import organizer.identity.mo.SkypeHandleType;
import organizer.identity.service.AbstractContactInformationService;
import organizer.identity.service.SkypeHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "skypeHandleService")
public class SkypeHandleServiceImpl extends AbstractContactInformationService<SkypeHandle, SkypeHandleBo> implements SkypeHandleService {

    @Autowired
    private SkypeHandleDao dao;

    public SkypeHandleServiceImpl() {
        super(SkypeHandleBo.class);
    }

    @Override
    public SkypeHandle getByType(ContactType contactType, Long contactId, SkypeHandleType type) {
        return super.getByType(contactType, contactId, type);
    }

    @Override
    protected SkypeHandleBo prepareForCreate(SkypeHandle mo) {
        return super.prepareForCreate(mo)
                .setType(mo.getType())
                .setUsername(mo.getUsername());
    }

    @Override
    protected SkypeHandleBo prepareForUpdate(SkypeHandleBo existingBo, SkypeHandle mo) {
        return super.prepareForUpdate(existingBo, mo)
                .setType(mo.getType())
                .setUsername(mo.getUsername());
    }

    @Override
    public SkypeHandleDao getDao() {
        return dao;
    }

    public void setDao(SkypeHandleDao dao) {
        this.dao = dao;
    }
}
