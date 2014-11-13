package organizer.identity.service;

import organizer.PaginatedCollection;
import organizer.identity.mo.GroupMember;
import organizer.identity.mo.ContactType;
import organizer.service.ModelObjectService;

import java.util.Collection;

public interface GroupMemberService extends ModelObjectService<GroupMember> {

    PaginatedCollection<GroupMember> getMembersByGroupId(Long groupId, int offset, int limit);
    PaginatedCollection<GroupMember> getMembersByContacts(ContactType contactType, Collection<Long> contactIds, int offset, int limit);
}
