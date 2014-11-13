package organizer.identity.dto;

import organizer.identity.mo.EmailAddress;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.EmailAddressType;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlRootElement(name = "emailAddress")
@XmlAccessorType( XmlAccessType.NONE)
@XmlType(propOrder = {
        EmailAddressDto.Elements.ID,
        EmailAddressDto.Elements.TENANT_ID,
        EmailAddressDto.Elements.CONTACT_TYPE,
        EmailAddressDto.Elements.CONTACT_ID,
        EmailAddressDto.Elements.TYPE,
        EmailAddressDto.Elements.EMAIL,
        EmailAddressDto.Elements.PRIMARY,
        EmailAddressDto.Elements.ACTIVE,
        EmailAddressDto.Elements.ACTIVE_FROM_DATE,
        EmailAddressDto.Elements.ACTIVE_TO_DATE,
        EmailAddressDto.Elements.LAST_UPDATE_DATE,
        EmailAddressDto.Elements.CREATION_DATE,
        EmailAddressDto.Elements.OPEN_CONTENT
})
@ApiModel(value = "An email address for a contact")
public class EmailAddressDto extends ContactInformationDto<EmailAddress> implements EmailAddress {

    @XmlElement(name = Elements.TYPE)
    @ApiModelProperty(value = "The type of the email address.  Either Work (WORK), Personal (PRSN), or Other (OTH).", required = true)
    private final EmailAddressType type;
    @XmlElement(name = Elements.EMAIL)
    @ApiModelProperty(value = "The email address for the contact.", required = true)
    private final String emailAddress;

    private EmailAddressDto() {
        this.type = null;
        this.emailAddress = null;
    }

    private EmailAddressDto(Builder builder) {
        super(builder);
        this.type = builder.getType();
        this.emailAddress = builder.getEmailAddress();
    }

    @Override
    public EmailAddress asModelObject() {
        return this;
    }

    @Override
    public EmailAddressDto.Builder asBuilder() {
        return Builder.create(this);
    }

    @Override
    public EmailAddressType getType() {
        return type;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    public static class Builder extends ContactInformationDto.Builder<EmailAddress> implements EmailAddress {

        private EmailAddressType type;
        private String emailAddress;

        private Builder() {
            this.type = null;
            this.emailAddress = null;
        }

        private Builder(EmailAddress emailAddress) {
            super(emailAddress);
            this.type = emailAddress.getType();
            this.emailAddress = emailAddress.getEmailAddress();
        }

        public static Builder create() {
            return new Builder();
        }

        public static Builder create(EmailAddress emailAddress) {
            return new Builder(emailAddress);
        }

        public EmailAddress build() {
            return new EmailAddressDto(this);
        }

        @Override
        public EmailAddressType getType() {
            return type;
        }

        public Builder setType(EmailAddressType type) {
            this.type = type;
            return this;
        }

        @Override
        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
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

    public static final class Adapter extends XmlAdapter<EmailAddressDto, EmailAddress> {
        @Override
        public EmailAddress unmarshal(EmailAddressDto emailAddress) throws Exception {
            return emailAddress;
        }

        @Override
        public EmailAddressDto marshal(EmailAddress emailAddress) throws Exception {
            return (EmailAddressDto) emailAddress;
        }
    }

    public static final class Elements extends ContactInformationDto.Elements {

        public static final String EMAIL = "emailAddress";

        private Elements() {
            throw new UnsupportedOperationException("Private constructor should never be invoked");
        }
    }
}
