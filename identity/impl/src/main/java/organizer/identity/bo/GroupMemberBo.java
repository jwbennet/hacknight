package organizer.identity.bo;

import organizer.identity.dao.GroupMemberDao;
import organizer.identity.dto.GroupMemberDto;
import organizer.identity.mo.GroupMember;
import organizer.bo.AbstractBusinessObject;
import organizer.identity.mo.ContactType;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "group_members")
@NamedQueries({
        @NamedQuery(name = GroupMemberDao.FIND_BY_GROUP_ID, query = GroupMemberDao.FIND_BY_GROUP_ID_QUERY),
        @NamedQuery(name = GroupMemberDao.COUNT_BY_GROUP_ID, query = GroupMemberDao.COUNT_BY_GROUP_ID_QUERY),
        @NamedQuery(name = GroupMemberDao.FIND_BY_CONTACTS, query = GroupMemberDao.FIND_BY_CONTACTS_QUERY),
        @NamedQuery(name = GroupMemberDao.COUNT_BY_CONTACTS, query = GroupMemberDao.COUNT_BY_CONTACTS_QUERY)
})
public class GroupMemberBo extends AbstractBusinessObject<GroupMember> implements GroupMember {

    @Column(name = "group_id")
    private Long groupId;
    @Column( name = "contact_type" )
    @Type( type = "org.jadira.usertype.corejava.PersistentEnum",
            parameters = {
                    @org.hibernate.annotations.Parameter( name = "enumClass", value = "organizer.identity.mo.ContactType" ),
                    @org.hibernate.annotations.Parameter( name = "identifierMethod", value = "getCode" ),
                    @org.hibernate.annotations.Parameter( name = "valueOfMethod", value = "fromCode" )
            } )
    private ContactType contactType;
    @Column(name = "contact_id")
    private Long contactId;

    @Override
    public GroupMember asImmutable() {
        return GroupMemberDto.Builder.create(this).build();
    }

    @Override
    public Long getGroupId() {
        return groupId;
    }

    public GroupMemberBo setGroupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    @Override
    public ContactType getContactType() {
        return contactType;
    }

    public GroupMemberBo setContactType(ContactType contactType) {
        this.contactType = contactType;
        return this;
    }

    @Override
    public Long getContactId() {
        return contactId;
    }

    public GroupMemberBo setContactId(Long contactId) {
        this.contactId = contactId;
        return this;
    }

    @Override
    public GroupMemberBo setId(Long id) {
        return (GroupMemberBo) super.setId(id);
    }

    @Override
    public GroupMemberBo setTenantId(Long tenantId) {
        return (GroupMemberBo) super.setTenantId(tenantId);
    }

    @Override
    public GroupMemberBo setActiveFromDate(DateTime activeFromDate) {
        return (GroupMemberBo) super.setActiveFromDate(activeFromDate);
    }

    @Override
    public GroupMemberBo setActiveToDate(DateTime activeToDate) {
        return (GroupMemberBo) super.setActiveToDate(activeToDate);
    }

    @Override
    public GroupMemberBo setLastUpdateDate(DateTime lastUpdateDate) {
        return (GroupMemberBo) super.setLastUpdateDate(lastUpdateDate);
    }

    @Override
    public GroupMemberBo setCreationDate(DateTime creationDate) {
        return (GroupMemberBo) super.setCreationDate(creationDate);
    }

    @Override
    public GroupMemberBo setVersionNumber(Long versionNumber) {
        return (GroupMemberBo) super.setVersionNumber(versionNumber);
    }
}
