package organizer.identity.dao;

import organizer.identity.bo.NameBo;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.Name;
import organizer.identity.mo.NameType;

public interface NameDao extends ContactInformationDao<Name, NameBo> {

    NameBo getByType(ContactType contactType, long contactId, NameType type);
}
