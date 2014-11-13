package organizer.identity.dto;

import organizer.dto.AbstractDataTransferObject;
import organizer.identity.mo.ContactInformation;
import organizer.identity.mo.ContactType;
import com.wordnik.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class ContactInformationDto<M extends ContactInformation> extends AbstractDataTransferObject<M> implements ContactInformation {

    @XmlElement(name = Elements.CONTACT_TYPE, required = true)
    @ApiModelProperty(value = "The type of the contact.  Either Person (P) or Group (G)", required = true)
    private final ContactType contactType;
    @XmlElement(name = Elements.CONTACT_ID, required = true)
    @ApiModelProperty(value = "The ID of the contact.  Either the ID of the person if the contact type is P or the ID of the group if the contact type is G", required = true)
    private final Long contactId;
    @XmlElement( name = Elements.PRIMARY, required = true )
    @ApiModelProperty(value = "Indicates if this object is primary contact information.", required = true)
    private final boolean primary;

    protected ContactInformationDto() {
        super();
        this.contactType = null;
        this.contactId = null;
        this.primary = false;
    }

    protected ContactInformationDto(Builder builder) {
        super(builder);
        this.contactType = builder.getContactType();
        this.contactId = builder.getContactId();
        this.primary = builder.isPrimary();
    }

    @Override
    public ContactType getContactType() {
        return contactType;
    }

    @Override
    public boolean isPrimary() {
        return primary;
    }    @Override
    public Long getContactId() {
        return contactId;
    }

    protected abstract static class Builder<M extends ContactInformation> extends AbstractDataTransferObject.Builder<M> implements ContactInformation {

        private boolean primary;
        private ContactType contactType;
        private Long contactId;

        protected Builder() {
            this.primary = false;
            this.contactType = ContactType.PERSON;
            this.contactId = null;
        }

        protected Builder(ContactInformation contactInformation) {
            super(contactInformation);
            this.primary = contactInformation.isPrimary();
            this.contactType = contactInformation.getContactType();
            this.contactId = contactInformation.getContactId();
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
        public boolean isPrimary() {
            return primary;
        }

        public Builder setPrimary(boolean primary) {
            this.primary = primary;
            return this;
        }
    }

    public static class Elements extends AbstractDataTransferObject.Elements {

        public static final String CONTACT_ID = "contactId";
        public static final String CONTACT_TYPE = "contactType";
        public static final String PRIMARY = "primary";
        public static final String TYPE = "type";
        protected Elements() {
            throw new UnsupportedOperationException("Private constructor should never be invoked");
        }
    }


}
