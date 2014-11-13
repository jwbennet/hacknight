package organizer.identity.dto;

import organizer.identity.mo.ContactType;
import organizer.identity.mo.PhoneNumber;
import organizer.identity.mo.PhoneNumberType;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlRootElement(name = "phoneNumber")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {
        PhoneNumberDto.Elements.ID,
        PhoneNumberDto.Elements.TENANT_ID,
        PhoneNumberDto.Elements.CONTACT_TYPE,
        PhoneNumberDto.Elements.CONTACT_ID,
        PhoneNumberDto.Elements.TYPE,
        PhoneNumberDto.Elements.PHONE_NUMBER,
        PhoneNumberDto.Elements.EXTENSION,
        PhoneNumberDto.Elements.PRIMARY,
        PhoneNumberDto.Elements.ACTIVE,
        PhoneNumberDto.Elements.ACTIVE_FROM_DATE,
        PhoneNumberDto.Elements.ACTIVE_TO_DATE,
        PhoneNumberDto.Elements.LAST_UPDATE_DATE,
        PhoneNumberDto.Elements.CREATION_DATE,
        PhoneNumberDto.Elements.OPEN_CONTENT
})
@ApiModel(value = "A phone number for a contact")
public class PhoneNumberDto extends ContactInformationDto<PhoneNumber> implements PhoneNumber {

    @XmlElement(name = Elements.TYPE)
    @ApiModelProperty(value = "The type of the phone number.  Either Home (HOME), Work (WORK), Mobile (MOBI), Fax (FAX), or Other (OTH).", required = true)
    private final PhoneNumberType type;
    @XmlElement(name = Elements.PHONE_NUMBER)
    @ApiModelProperty(value = "The phone number for the contact.", required = true)
    private final String phoneNumber;
    @XmlElement(name = Elements.EXTENSION)
    @ApiModelProperty(value = "The extension for the phone number.")
    private final String extension;

    private PhoneNumberDto() {
        this.type = null;
        this.phoneNumber = null;
        this.extension = null;
    }

    private PhoneNumberDto(Builder builder) {
        super(builder);
        this.type = builder.getType();
        this.phoneNumber = builder.getPhoneNumber();
        this.extension = builder.getExtension();
    }

    @Override
    public PhoneNumber asModelObject() {
        return this;
    }

    @Override
    public PhoneNumberDto.Builder asBuilder() {
        return Builder.create(this);
    }

    @Override
    public PhoneNumberType getType() {
        return type;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getExtension() {
        return extension;
    }

    public static class Builder extends ContactInformationDto.Builder<PhoneNumber> implements PhoneNumber {

        private PhoneNumberType type;
        private String phoneNumber;
        private String extension;

        private Builder() {
            this.type = null;
            this.phoneNumber = null;
            this.extension = null;
        }

        private Builder(PhoneNumber phoneNumber) {
            super(phoneNumber);
            this.type = phoneNumber.getType();
            this.phoneNumber = phoneNumber.getPhoneNumber();
            this.extension = phoneNumber.getExtension();
        }

        public static Builder create() {
            return new Builder();
        }

        public static Builder create(PhoneNumber phoneNumber) {
            return new Builder(phoneNumber);
        }

        public PhoneNumber build() {
            return new PhoneNumberDto(this);
        }

        @Override
        public PhoneNumberType getType() {
            return type;
        }

        public Builder setType(PhoneNumberType type) {
            this.type = type;
            return this;
        }

        @Override
        public String getPhoneNumber() {
            return phoneNumber;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        @Override
        public String getExtension() {
            return extension;
        }

        public Builder setExtension(String extension) {
            this.extension = extension;
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

    public static final class Adapter extends XmlAdapter<PhoneNumberDto, PhoneNumber> {
        @Override
        public PhoneNumber unmarshal(PhoneNumberDto phoneNumber) throws Exception {
            return phoneNumber;
        }

        @Override
        public PhoneNumberDto marshal(PhoneNumber phoneNumber) throws Exception {
            return (PhoneNumberDto) phoneNumber;
        }
    }

    public static final class Elements extends ContactInformationDto.Elements {

        public static final String PHONE_NUMBER = "phoneNumber";
        public static final String EXTENSION = "extension";

        private Elements() {
            throw new UnsupportedOperationException("Private constructor should never be invoked");
        }
    }
}
