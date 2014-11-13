package organizer.identity.dto;

import organizer.dto.AbstractDataTransferObject;
import organizer.identity.mo.Group;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlRootElement(name = "group")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {
        GroupDto.Elements.ID,
        GroupDto.Elements.TENANT_ID,
        GroupDto.Elements.ALIAS,
        GroupDto.Elements.ACTIVE,
        GroupDto.Elements.ACTIVE_FROM_DATE,
        GroupDto.Elements.ACTIVE_TO_DATE,
        GroupDto.Elements.LAST_UPDATE_DATE,
        GroupDto.Elements.CREATION_DATE,
        PersonDto.Elements.OPEN_CONTENT
})
@ApiModel(value = "A group is a contact which is a collection of people")
public class GroupDto extends AbstractDataTransferObject<Group> implements Group {

    @XmlElement(name = Elements.ALIAS)
    @ApiModelProperty(value = "A unique, short identifier for a group. Must be between 6 and 40 characters in length.", required = true)
    private final String alias;

    private GroupDto() {
        this.alias = null;
    }

    private GroupDto(Builder builder) {
        super(builder);
        this.alias = builder.getAlias();
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public Group asModelObject() {
        return this;
    }

    @Override
    public GroupDto.Builder asBuilder() {
        return Builder.create(this);
    }

    public static class Builder extends AbstractDataTransferObject.Builder<Group> implements Group {

        private String alias;

        private Builder() {
            this.alias = null;
        }

        private Builder(Group group) {
            super(group);
            this.alias = group.getAlias();
        }

        public static Builder create() {
            return new Builder();
        }

        public static Builder create(Group group) {
            return new Builder(group);
        }

        public GroupDto build() {
            return new GroupDto(this);
        }

        @Override
        public String getAlias() {
            return alias;
        }

        public Builder setAlias(String alias) {
            this.alias = alias;
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

    public static final class Adapter extends XmlAdapter<GroupDto, Group> {
        @Override
        public Group unmarshal(GroupDto group) throws Exception {
            return group;
        }

        @Override
        public GroupDto marshal(Group group) throws Exception {
            return (GroupDto) group;
        }
    }

    public static final class Elements extends AbstractDataTransferObject.Elements {

        public static final String ALIAS = "alias";

        private Elements() {
            throw new UnsupportedOperationException("Private constructor should never be invoked");
        }
    }
}
