package organizer.identity.dao.impl;

import organizer.PaginatedArrayList;
import organizer.PaginatedCollection;
import organizer.dao.impl.AbstractBusinessObjectDao;
import organizer.identity.bo.GroupBo;
import organizer.identity.dao.GroupDao;
import organizer.identity.mo.Group;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class GroupDaoImpl extends AbstractBusinessObjectDao<Group, GroupBo> implements GroupDao {

    public GroupDaoImpl() {
        super(GroupBo.class);
    }

    @Override
    public GroupBo getGroupByAlias(String alias) {
        return getEntityManager()
                .createNamedQuery(GroupDao.FIND_BY_ALIAS, GroupBo.class)
                .setParameter("alias", alias)
                .getSingleResult();
    }

    @Override
    public PaginatedCollection<GroupBo> getByGroupIds(Collection<Long> groupIds, int offset, int limit) {
        Long count = getEntityManager()
                .createNamedQuery(GroupDao.COUNT_BY_GROUP_IDS, Long.class)
                .setParameter("groupIds", groupIds)
                .getSingleResult();

        List<GroupBo> groups = getEntityManager()
                .createNamedQuery(GroupDao.FIND_BY_GROUP_IDS, GroupBo.class)
                .setParameter("groupIds", groupIds)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
        return new PaginatedArrayList<>(groups)
                .setOffset(offset)
                .setLimit(limit)
                .setCount(count);
    }
}
