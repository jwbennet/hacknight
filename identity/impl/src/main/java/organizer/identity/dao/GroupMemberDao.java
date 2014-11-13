package organizer.identity.dao;

import organizer.PaginatedCollection;
import organizer.dao.BusinessObjectDao;
import organizer.identity.bo.GroupMemberBo;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.GroupMember;

import java.util.Collection;

public interface GroupMemberDao extends BusinessObjectDao<GroupMember, GroupMemberBo> {

    public static final String FIND_BY_GROUP_ID = "GroupMember.findByGroupId";
    public static final String FIND_BY_GROUP_ID_QUERY = "SELECT gm FROM GroupMemberBo gm WHERE gm.groupId = :groupId AND (gm.activeFromDate IS NULL OR gm.activeFromDate < CURRENT_TIMESTAMP) AND (gm.activeToDate IS NULL OR gm.activeToDate > CURRENT_TIMESTAMP)";
    public static final String COUNT_BY_GROUP_ID = "GroupMember.countByGroupId";
    public static final String COUNT_BY_GROUP_ID_QUERY = "SELECT COUNT(gm) FROM GroupMemberBo gm WHERE gm.groupId = :groupId AND (gm.activeFromDate IS NULL OR gm.activeFromDate < CURRENT_TIMESTAMP) AND (gm.activeToDate IS NULL OR gm.activeToDate > CURRENT_TIMESTAMP)";
    public static final String FIND_BY_CONTACTS = "GroupMember.findGroupIdsByContacts";
    public static final String FIND_BY_CONTACTS_QUERY = "SELECT gm FROM GroupMemberBo gm WHERE gm.contactType = :contactType AND gm.contactId IN :contactIds AND (gm.activeFromDate IS NULL OR gm.activeFromDate < CURRENT_TIMESTAMP) AND (gm.activeToDate IS NULL OR gm.activeToDate > CURRENT_TIMESTAMP)";
    public static final String COUNT_BY_CONTACTS = "GroupMember.countGroupIdsByContacts";
    public static final String COUNT_BY_CONTACTS_QUERY = "SELECT COUNT(gm) FROM GroupMemberBo gm WHERE gm.contactType = :contactType AND gm.contactId IN :contactIds AND (gm.activeFromDate IS NULL OR gm.activeFromDate < CURRENT_TIMESTAMP) AND (gm.activeToDate IS NULL OR gm.activeToDate > CURRENT_TIMESTAMP)";

    PaginatedCollection<GroupMemberBo> getMembersByGroupId(Long groupId, int offset, int limit);
    PaginatedCollection<GroupMemberBo> getMembersByContacts(ContactType contactType, Collection<Long> contactIds, int offset, int limit);
}
