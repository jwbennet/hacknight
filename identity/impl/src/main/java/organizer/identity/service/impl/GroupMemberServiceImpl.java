package organizer.identity.service.impl;

import organizer.PaginatedCollection;
import organizer.identity.bo.GroupMemberBo;
import organizer.identity.dao.GroupMemberDao;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.GroupMember;
import organizer.identity.service.GroupMemberService;
import organizer.service.impl.AbstractModelObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service(value = "groupMemberService")
public class GroupMemberServiceImpl extends AbstractModelObjectService<GroupMember, GroupMemberBo> implements GroupMemberService {

    @Autowired
    private GroupMemberDao dao;

    public GroupMemberServiceImpl() {
        super(GroupMemberBo.class);
    }

    @Override
    public PaginatedCollection<GroupMember> getMembersByGroupId(Long groupId, int offset, int limit) {
        return convertCollection(getDao().getMembersByGroupId(groupId, offset, limit));
    }

    @Override
    public PaginatedCollection<GroupMember> getMembersByContacts(ContactType contactType, Collection<Long> contactIds, int offset, int limit) {
        return convertCollection(getDao().getMembersByContacts(contactType, contactIds, offset, limit));
    }

    @Override
    protected GroupMemberBo prepareForCreate(GroupMember mo) {
        return super.prepareForCreate(mo)
                .setGroupId(mo.getGroupId())
                .setContactType(mo.getContactType())
                .setContactId(mo.getContactId());
    }

    @Override
    protected GroupMemberBo prepareForUpdate(GroupMemberBo existingBo, GroupMember mo) {
        return super.prepareForUpdate(existingBo, mo)
                .setGroupId(mo.getGroupId())
                .setContactType(mo.getContactType())
                .setContactId(mo.getContactId());
    }

    @Override
    protected GroupMemberDao getDao() {
        return dao;
    }

    public void setDao(GroupMemberDao dao) {
        this.dao = dao;
    }
}
