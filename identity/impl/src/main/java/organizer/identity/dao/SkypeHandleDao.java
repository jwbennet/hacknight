package organizer.identity.dao;

import organizer.identity.bo.SkypeHandleBo;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.SkypeHandle;
import organizer.identity.mo.SkypeHandleType;

public interface SkypeHandleDao extends ContactInformationDao<SkypeHandle, SkypeHandleBo> {

    SkypeHandleBo getByType(ContactType contactType, long contactId, SkypeHandleType type);
}
