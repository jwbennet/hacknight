package organizer.identity.bo;

import organizer.identity.dao.GroupDao;
import organizer.identity.dto.GroupDto;
import organizer.identity.mo.Group;
import organizer.bo.AbstractBusinessObject;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "groups")
@NamedQueries({
        @NamedQuery(name = GroupDao.FIND_BY_ALIAS, query = GroupDao.FIND_BY_ALIAS_QUERY),
        @NamedQuery(name = GroupDao.FIND_BY_GROUP_IDS, query = GroupDao.FIND_BY_GROUP_IDS_QUERY),
        @NamedQuery(name = GroupDao.COUNT_BY_GROUP_IDS, query = GroupDao.COUNT_BY_GROUP_IDS_QUERY)
})
public class GroupBo extends AbstractBusinessObject<Group> implements Group {

    @Column(name = "alias")
    private String alias;

    @Override
    public Group asImmutable() {
        return GroupDto.Builder.create(this).build();
    }

    @Override
    public String getAlias() {
        return alias;
    }

    public GroupBo setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    @Override
    public GroupBo setId(Long id) {
        return (GroupBo) super.setId(id);
    }

    @Override
    public GroupBo setTenantId(Long tenantId) {
        return (GroupBo) super.setTenantId(tenantId);
    }

    @Override
    public GroupBo setActiveFromDate(DateTime activeFromDate) {
        return (GroupBo) super.setActiveFromDate(activeFromDate);
    }

    @Override
    public GroupBo setActiveToDate(DateTime activeToDate) {
        return (GroupBo) super.setActiveToDate(activeToDate);
    }

    @Override
    public GroupBo setLastUpdateDate(DateTime lastUpdateDate) {
        return (GroupBo) super.setLastUpdateDate(lastUpdateDate);
    }

    @Override
    public GroupBo setCreationDate(DateTime creationDate) {
        return (GroupBo) super.setCreationDate(creationDate);
    }

    @Override
    public GroupBo setVersionNumber(Long versionNumber) {
        return (GroupBo) super.setVersionNumber(versionNumber);
    }
}
