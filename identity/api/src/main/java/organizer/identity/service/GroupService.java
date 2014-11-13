package organizer.identity.service;

import organizer.PaginatedCollection;
import organizer.identity.mo.Group;
import organizer.service.ModelObjectService;
import organizer.identity.mo.ContactType;

import java.util.Collection;

public interface GroupService extends ModelObjectService<Group> {

    Group getGroupByAlias(String alias);
    PaginatedCollection<Group> getByGroupIds(Collection<Long> groupIds, int offset, int limit);
    PaginatedCollection<Group> getGroupsByContactId(ContactType contactType, Long contactId, int offset, int limit);

}
