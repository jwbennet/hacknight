package organizer.identity.dto;

import organizer.identity.mo.ContactType;
import organizer.identity.mo.Name;
import organizer.identity.mo.NameType;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlRootElement(name = "name")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {
        NameDto.Elements.ID,
        NameDto.Elements.TENANT_ID,
        NameDto.Elements.CONTACT_TYPE,
        NameDto.Elements.CONTACT_ID,
        NameDto.Elements.TYPE,
        NameDto.Elements.GIVEN_NAME,
        NameDto.Elements.MIDDLE_NAME,
        NameDto.Elements.SURNAME,
        NameDto.Elements.SUFFIX_NAME,
        NameDto.Elements.DISPLAY_NAME,
        NameDto.Elements.PRIMARY,
        NameDto.Elements.ACTIVE,
        NameDto.Elements.ACTIVE_FROM_DATE,
        NameDto.Elements.ACTIVE_TO_DATE,
        NameDto.Elements.LAST_UPDATE_DATE,
        NameDto.Elements.CREATION_DATE,
        NameDto.Elements.OPEN_CONTENT
})
@ApiModel(value = "A name for a contact")
public class NameDto extends ContactInformationDto<Name> implements Name {

    @XmlElement(name = Elements.TYPE)
    @ApiModelProperty(value = "The type of the name.  Either Primary (PRM), Preferred (PRFR), or Other (OTH).", required = true)
    private final NameType type;
    @XmlElement(name = Elements.GIVEN_NAME)
    @ApiModelProperty(value = "The given name for the contact")
    private final String givenName;
    @XmlElement(name = Elements.MIDDLE_NAME)
    @ApiModelProperty(value = "The middle name for the contact")
    private final String middleName;
    @XmlElement(name = Elements.SURNAME)
    @ApiModelProperty(value = "The surname for the contact.  This field also holds the name for the group if the contact is a group.", required = true)
    private final String surname;
    @XmlElement(name = Elements.SUFFIX_NAME)
    @ApiModelProperty(value = "The suffix name for the contact")
    private final String suffix;

    private NameDto() {
        this.type = null;
        this.givenName = null;
        this.middleName = null;
        this.surname = null;
        this.suffix = null;
    }

    private NameDto(Builder builder) {
        super(builder);
        this.type = builder.getType();
        this.givenName = builder.getGivenName();
        this.middleName = builder.getMiddleName();
        this.surname = builder.getSurname();
        this.suffix = builder.getSuffix();
    }

    @Override
    @XmlElement(name = Elements.DISPLAY_NAME)
    @ApiModelProperty(value = "This is a calculated value to assist with displaying the name.  If it is provided to create/update operations it will be ignored.")
    public String getDisplayName() {
        return Name.getDisplayName(this);
    }

    @Override
    public Name asModelObject() {
        return this;
    }

    @Override
    public NameDto.Builder asBuilder() {
        return Builder.create(this);
    }

    @Override
    public NameType getType() {
        return type;
    }

    @Override
    public String getGivenName() {
        return givenName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    public String getSuffix() {
        return suffix;
    }

    public static class Builder extends ContactInformationDto.Builder<Name> implements Name {

        private NameType type;
        private String givenName;
        private String middleName;
        private String surname;
        private String suffix;

        private Builder() {
            this.type = null;
            this.givenName = null;
            this.middleName = null;
            this.surname = null;
            this.suffix = null;
        }

        private Builder(Name name) {
            super(name);
            this.type = name.getType();
            this.givenName = name.getGivenName();
            this.middleName = name.getMiddleName();
            this.surname = name.getSurname();
            this.suffix = name.getSuffix();
        }

        public static Builder create() {
            return new Builder();
        }

        public static Builder create(Name name) {
            return new Builder(name);
        }

        public Name build() {
            return new NameDto(this);
        }

        @Override
        public NameType getType() {
            return type;
        }

        public Builder setType(NameType type) {
            this.type = type;
            return this;
        }

        @Override
        public String getGivenName() {
            return givenName;
        }

        public Builder setGivenName(String givenName) {
            this.givenName = givenName;
            return this;
        }

        @Override
        public String getMiddleName() {
            return middleName;
        }

        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        @Override
        public String getSurname() {
            return surname;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        @Override
        public String getSuffix() {
            return suffix;
        }

        public Builder setSuffix(String suffix) {
            this.suffix = suffix;
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

    public static final class Adapter extends XmlAdapter<NameDto, Name> {
        @Override
        public Name unmarshal(NameDto name) throws Exception {
            return name;
        }

        @Override
        public NameDto marshal(Name name) throws Exception {
            return (NameDto) name;
        }
    }

    public static final class Elements extends ContactInformationDto.Elements {

        public static final String GIVEN_NAME = "givenName";
        public static final String MIDDLE_NAME = "middleName";
        public static final String SURNAME = "surname";
        public static final String SUFFIX_NAME = "suffix";
        public static final String DISPLAY_NAME = "displayName";

        private Elements() {
            throw new UnsupportedOperationException("Private constructor should never be invoked");
        }
    }
}
