package organizer.identity.dto;

import organizer.identity.mo.ContactType;
import organizer.identity.mo.SkypeHandle;
import organizer.identity.mo.SkypeHandleType;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlRootElement(name = "skypeHandle")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {
        SkypeHandleDto.Elements.ID,
        SkypeHandleDto.Elements.TENANT_ID,
        SkypeHandleDto.Elements.CONTACT_TYPE,
        SkypeHandleDto.Elements.CONTACT_ID,
        SkypeHandleDto.Elements.TYPE,
        SkypeHandleDto.Elements.USERNAME,
        SkypeHandleDto.Elements.PRIMARY,
        SkypeHandleDto.Elements.ACTIVE,
        SkypeHandleDto.Elements.ACTIVE_FROM_DATE,
        SkypeHandleDto.Elements.ACTIVE_TO_DATE,
        SkypeHandleDto.Elements.LAST_UPDATE_DATE,
        SkypeHandleDto.Elements.CREATION_DATE,
        SkypeHandleDto.Elements.OPEN_CONTENT
})
@ApiModel(value = "A Skype handle for a contact")
public class SkypeHandleDto extends ContactInformationDto<SkypeHandle> implements SkypeHandle {

    @XmlElement(name = Elements.TYPE)
    @ApiModelProperty(value = "The type of the Skype handle.  Either Work (WORK), Personal (PRSN), or Other (OTH).", required = true)
    private final SkypeHandleType type;
    @XmlElement(name = Elements.USERNAME)
    @ApiModelProperty(value = "The Skype username for the contact.", required = true)
    private String username;

    private SkypeHandleDto() {
        this.type = null;
        this.username = null;
    }

    private SkypeHandleDto(Builder builder) {
        super(builder);
        this.type = builder.getType();
        this.username = builder.getUsername();
    }

    @Override
    public SkypeHandle asModelObject() {
        return this;
    }

    @Override
    public SkypeHandleDto.Builder asBuilder() {
        return Builder.create(this);
    }

    @Override
    public SkypeHandleType getType() {
        return type;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public static class Builder extends ContactInformationDto.Builder<SkypeHandle> implements SkypeHandle {

        private SkypeHandleType type;
        private String username;

        private Builder() {
            this.type = null;
            this.username = null;
        }

        private Builder(SkypeHandle skypeHandle) {
            super(skypeHandle);
            this.type = skypeHandle.getType();
            this.username = skypeHandle.getUsername();
        }

        public static Builder create() {
            return new Builder();
        }

        public static Builder create(SkypeHandle skypeHandle) {
            return new Builder(skypeHandle);
        }

        public SkypeHandle build() {
            return new SkypeHandleDto(this);
        }

        @Override
        public SkypeHandleType getType() {
            return type;
        }

        public Builder setType(SkypeHandleType type) {
            this.type = type;
            return this;
        }

        @Override
        public String getUsername() {
            return username;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        @Override
        public Builder setContactType(ContactType contactType) {
            return (Builder)super.setContactType(contactType);
        }

        @Override
        public Builder setContactId(Long contactId) {
            return (Builder)super.setContactId(contactId);
        }

        @Override
        public Builder setPrimary(boolean primary) {
            return (Builder)super.setPrimary(primary);
        }

        @Override
        public Builder setId(Long id) {
            return (Builder)super.setId(id);
        }

        @Override
        public Builder setTenantId(Long tenantId) {
            return (Builder)super.setTenantId(tenantId);
        }

        @Override
        public Builder setActiveFromDate(DateTime activeFromDate) {
            return (Builder)super.setActiveFromDate(activeFromDate);
        }

        @Override
        public Builder setActiveToDate(DateTime activeToDate) {
            return (Builder)super.setActiveToDate(activeToDate);
        }

        @Override
        public Builder setLastUpdateDate(DateTime lastUpdateDate) {
            return (Builder)super.setLastUpdateDate(lastUpdateDate);
        }

        @Override
        public Builder setCreationDate(DateTime creationDate) {
            return (Builder)super.setCreationDate(creationDate);
        }
    }

    public static final class Adapter extends XmlAdapter<SkypeHandleDto, SkypeHandle> {
        @Override
        public SkypeHandle unmarshal(SkypeHandleDto skypeHandle) throws Exception {
            return skypeHandle;
        }

        @Override
        public SkypeHandleDto marshal(SkypeHandle skypeHandle) throws Exception {
            return (SkypeHandleDto) skypeHandle;
        }
    }

    public static final class Elements extends ContactInformationDto.Elements {

        public static final String USERNAME = "username";

        private Elements() {
            throw new UnsupportedOperationException("Private constructor should never be invoked");
        }
    }
}
