package organizer.identity.dao.impl;

import organizer.PaginatedArrayList;
import organizer.PaginatedCollection;
import organizer.dao.impl.AbstractBusinessObjectDao;
import organizer.identity.bo.GroupMemberBo;
import organizer.identity.dao.GroupMemberDao;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.GroupMember;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class GroupMemberDaoImpl extends AbstractBusinessObjectDao<GroupMember, GroupMemberBo> implements GroupMemberDao {

    public GroupMemberDaoImpl() {
        super(GroupMemberBo.class);
    }

    @Override
    public PaginatedCollection<GroupMemberBo> getMembersByGroupId(Long groupId, int offset, int limit) {
        Long count = getEntityManager()
                .createNamedQuery(GroupMemberDao.COUNT_BY_GROUP_ID, Long.class)
                .setParameter("groupId", groupId)
                .getSingleResult();

        List<GroupMemberBo> members = getEntityManager()
                .createNamedQuery(GroupMemberDao.FIND_BY_GROUP_ID, GroupMemberBo.class)
                .setParameter("groupId", groupId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
        return new PaginatedArrayList<>(members)
                .setOffset(offset)
                .setLimit(limit)
                .setCount(count);
    }

    @Override
    public PaginatedCollection<GroupMemberBo> getMembersByContacts(ContactType contactType, Collection<Long> contactIds, int offset, int limit) {
        Long count = getEntityManager()
                .createNamedQuery(GroupMemberDao.COUNT_BY_CONTACTS, Long.class)
                .setParameter("contactType", contactType)
                .setParameter("contactIds", contactIds)
                .getSingleResult();

        List<GroupMemberBo> members = getEntityManager()
                .createNamedQuery(GroupMemberDao.FIND_BY_CONTACTS, GroupMemberBo.class)
                .setParameter("contactType", contactType)
                .setParameter("contactIds", contactIds)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
        return new PaginatedArrayList<>(members)
                .setOffset(offset)
                .setLimit(limit)
                .setCount(count);
    }
}
