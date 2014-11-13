package organizer.identity.service;

import organizer.identity.mo.ContactType;
import organizer.identity.mo.SkypeHandleType;
import organizer.identity.mo.SkypeHandle;

public interface SkypeHandleService extends ContactInformationService<SkypeHandle> {

    SkypeHandle getByType(ContactType contactType, Long contactId, SkypeHandleType type);
}
