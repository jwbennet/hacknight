package organizer.identity.dao;

import organizer.PaginatedCollection;
import organizer.dao.BusinessObjectDao;
import organizer.identity.bo.GroupBo;
import organizer.identity.mo.Group;

import java.util.Collection;

public interface GroupDao extends BusinessObjectDao<Group, GroupBo> {

    public static final String FIND_BY_ALIAS = "Group.findByAlias";
    public static final String FIND_BY_ALIAS_QUERY = "SELECT g FROM GroupBo g WHERE g.alias = :alias AND (g.activeFromDate IS NULL OR g.activeFromDate < CURRENT_TIMESTAMP) AND (g.activeToDate IS NULL OR g.activeToDate > CURRENT_TIMESTAMP)";
    public static final String FIND_BY_GROUP_IDS = "Group.findByGroupIds";
    public static final String FIND_BY_GROUP_IDS_QUERY = "SELECT g FROM GroupBo g WHERE g.id IN :groupIds AND (g.activeFromDate IS NULL OR g.activeFromDate < CURRENT_TIMESTAMP) AND (g.activeToDate IS NULL OR g.activeToDate > CURRENT_TIMESTAMP)";
    public static final String COUNT_BY_GROUP_IDS = "Group.countByGroupIds";
    public static final String COUNT_BY_GROUP_IDS_QUERY = "SELECT COUNT(g) FROM GroupBo g WHERE g.id IN :groupIds AND (g.activeFromDate IS NULL OR g.activeFromDate < CURRENT_TIMESTAMP) AND (g.activeToDate IS NULL OR g.activeToDate > CURRENT_TIMESTAMP)";

    GroupBo getGroupByAlias(String alias);
    PaginatedCollection<GroupBo> getByGroupIds(Collection<Long> groupIds, int offset, int limit);
}
