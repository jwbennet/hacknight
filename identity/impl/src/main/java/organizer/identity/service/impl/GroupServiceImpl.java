package organizer.identity.service.impl;

import organizer.PaginatedCollection;
import organizer.identity.bo.GroupBo;
import organizer.identity.dao.GroupDao;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.Group;
import organizer.identity.mo.GroupMember;
import organizer.identity.service.GroupMemberService;
import organizer.identity.service.GroupService;
import organizer.service.impl.AbstractModelObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "groupService")
public class GroupServiceImpl extends AbstractModelObjectService<Group, GroupBo> implements GroupService {

    @Autowired
    private GroupDao dao;
    @Autowired
    private GroupMemberService groupMemberService;

    public GroupServiceImpl() {
        super(GroupBo.class);
    }

    @Override
    public Group getGroupByAlias(String alias) {
        return getDao().getGroupByAlias(alias).asImmutable();
    }

    @Override
    public PaginatedCollection<Group> getByGroupIds(Collection<Long> groupIds, int offset, int limit) {
        return convertCollection(getDao().getByGroupIds(groupIds, offset, limit));
    }

    @Override
    public PaginatedCollection<Group> getGroupsByContactId(ContactType contactType, Long contactId, int offset, int limit) {
        Set<Long> groupIds = getGroupIdsFromMembership(contactType, Collections.singletonList(contactId), offset, limit);
        return getByGroupIds(groupIds, offset, limit);
    }

    private Set<Long> getGroupIdsFromMembership(ContactType contactType, Collection<Long> contactIds, int offset, int limit) {
        PaginatedCollection<GroupMember> members = getGroupMemberService().getMembersByContacts(contactType, contactIds, offset, limit);
        int memberOffset = limit;
        while(members.size() < members.getCount()) {
            members.addAll(getGroupMemberService().getMembersByContacts(contactType, contactIds, memberOffset, limit));
            memberOffset += limit;
        }
        Set<Long> groupIds = members
                .stream()
                .map(GroupMember::getGroupId)
                .collect(Collectors.toSet());
        if(CollectionUtils.isEmpty(groupIds)) {
            return groupIds;
        } else {
            groupIds.addAll(getGroupIdsFromMembership(ContactType.GROUP, groupIds, offset, limit));
            return groupIds;
        }

    }

    @Override
    protected GroupBo prepareForCreate(Group mo) {
        return super.prepareForCreate(mo)
                .setAlias(mo.getAlias());
    }

    @Override
    protected GroupBo prepareForUpdate(GroupBo existingBo, Group mo) {
        return super.prepareForUpdate(existingBo, mo)
                .setAlias(mo.getAlias());
    }

    @Override
    protected GroupDao getDao() {
        return dao;
    }

    public void setDao(GroupDao dao) {
        this.dao = dao;
    }

    public GroupMemberService getGroupMemberService() {
        return groupMemberService;
    }

    public void setGroupMemberService(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }
}
