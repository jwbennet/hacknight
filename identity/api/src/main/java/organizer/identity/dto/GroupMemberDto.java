package organizer.identity.dto;

import organizer.dto.AbstractDataTransferObject;
import organizer.identity.mo.GroupMember;
import organizer.identity.mo.ContactType;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlRootElement(name = "groupMember")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {
        GroupMemberDto.Elements.ID,
        GroupMemberDto.Elements.TENANT_ID,
        GroupMemberDto.Elements.GROUP_ID,
        GroupMemberDto.Elements.CONTACT_TYPE,
        GroupMemberDto.Elements.CONTACT_ID,
        GroupMemberDto.Elements.ACTIVE,
        GroupMemberDto.Elements.ACTIVE_FROM_DATE,
        GroupMemberDto.Elements.ACTIVE_TO_DATE,
        GroupMemberDto.Elements.LAST_UPDATE_DATE,
        GroupMemberDto.Elements.CREATION_DATE,
        GroupMemberDto.Elements.OPEN_CONTENT
})
@ApiModel(value = "A member of a group")
public class GroupMemberDto extends AbstractDataTransferObject<GroupMember> implements GroupMember {

    @XmlElement(name = Elements.GROUP_ID)
    @ApiModelProperty(value = "The ID of the group which this is a membership for")
    private final Long groupId;
    @XmlElement(name = Elements.CONTACT_TYPE)
    @ApiModelProperty(value = "The type of the contact which is a member in the group.  Either Person (P) or Group (G)", required = true)
    private final ContactType contactType;
    @XmlElement(name = Elements.CONTACT_ID)
    @ApiModelProperty(value = "The ID of the contact which is a member in the group.  Either the ID of the person if the contact type is P or the ID of the group if the contact type is G", required = true)
    private final Long contactId;

    private GroupMemberDto() {
        this.groupId = null;
        this.contactType = null;
        this.contactId = null;
    }

    private GroupMemberDto(Builder builder) {
        super(builder);
        this.groupId = builder.getGroupId();
        this.contactType = builder.getContactType();
        this.contactId = builder.getContactId();
    }

    @Override
    public Long getGroupId() {
        return groupId;
    }

    @Override
    public ContactType getContactType() {
        return contactType;
    }

    @Override
    public Long getContactId() {
        return contactId;
    }

    @Override
    public GroupMember asModelObject() {
        return this;
    }

    @Override
    public GroupMemberDto.Builder asBuilder() {
        return Builder.create(this);
    }

    public static class Builder extends AbstractDataTransferObject.Builder<GroupMember> implements GroupMember {

        private Long groupId;
        private ContactType contactType;
        private Long contactId;

        private Builder() {
            this.groupId = null;
            this.contactType = null;
            this.contactId = null;
        }

        private Builder(GroupMember groupMember) {
            super(groupMember);
            this.groupId = groupMember.getGroupId();
            this.contactType = groupMember.getContactType();
            this.contactId = groupMember.getContactId();
        }

        public static Builder create() {
            return new Builder();
        }

        public static Builder create(GroupMember groupMember) {
            return new Builder(groupMember);
        }

        public GroupMemberDto build() {
            return new GroupMemberDto(this);
        }

        @Override
        public Long getGroupId() {
            return groupId;
        }

        public Builder setGroupId(Long groupId) {
            this.groupId = groupId;
            return this;
        }

        @Override
        public ContactType getContactType() {
            return contactType;
        }

        public Builder setContactType(ContactType contactType) {
            this.contactType = contactType;
            return this;
        }

        @Override
        public Long getContactId() {
            return contactId;
        }

        public Builder setContactId(Long contactId) {
            this.contactId = contactId;
            return this;
        }

        @Override
        public Builder setId(Long id) {
            return (Builder) super.setId(id);
        }

        @Override
        public Builder setTenantId(Long tenantId) {
            return (Builder) super.setTenantId(tenantId);
        }

        @Override
        public Builder setActiveFromDate(DateTime activeFromDate) {
            return (Builder) super.setActiveFromDate(activeFromDate);
        }

        @Override
        public Builder setActiveToDate(DateTime activeToDate) {
            return (Builder) super.setActiveToDate(activeToDate);
        }

        @Override
        public Builder setLastUpdateDate(DateTime lastUpdateDate) {
            return (Builder) super.setLastUpdateDate(lastUpdateDate);
        }

        @Override
        public Builder setCreationDate(DateTime creationDate) {
            return (Builder) super.setCreationDate(creationDate);
        }
    }

    public static final class Adapter extends XmlAdapter<GroupMemberDto, GroupMember> {
        @Override
        public GroupMember unmarshal(GroupMemberDto groupMember) throws Exception {
            return groupMember;
        }

        @Override
        public GroupMemberDto marshal(GroupMember groupMember) throws Exception {
            return (GroupMemberDto) groupMember;
        }
    }

    public static final class Elements extends AbstractDataTransferObject.Elements {

        public static final String GROUP_ID = "groupId";
        public static final String CONTACT_TYPE = "contactType";
        public static final String CONTACT_ID = "contactId";

        private Elements() {
            throw new UnsupportedOperationException("Private constructor should never be invoked");
        }
    }
}
