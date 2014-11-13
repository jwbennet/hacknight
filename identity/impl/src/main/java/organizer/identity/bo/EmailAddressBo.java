package organizer.identity.bo;

import organizer.identity.dto.EmailAddressDto;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.EmailAddress;
import organizer.identity.mo.EmailAddressType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "email_addresses")
public class EmailAddressBo extends AbstractContactInformationBo<EmailAddress> implements EmailAddress {

    @Column(name = "type")
    @Type( type = "org.jadira.usertype.corejava.PersistentEnum",
            parameters = {
                    @Parameter( name = "enumClass", value = "organizer.identity.mo.EmailAddressType" ),
                    @Parameter( name = "identifierMethod", value = "getCode" ),
                    @Parameter( name = "valueOfMethod", value = "fromCode" )
            } )
    private EmailAddressType type;
    @Column(name = "email")
    private String emailAddress;

    @Override
    public EmailAddress asImmutable() {
        return EmailAddressDto.Builder.create(this).build();
    }

    @Override
    public EmailAddressType getType() {
        return type;
    }

    public EmailAddressBo setType(EmailAddressType type) {
        this.type = type;
        return this;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    public EmailAddressBo setEmailAddress(String email) {
        this.emailAddress = email;
        return this;
    }

    @Override
    public EmailAddressBo setContactId(Long contactId) {
        return (EmailAddressBo)super.setContactId(contactId);
    }

    @Override
    public EmailAddressBo setContactType(ContactType contactType) {
        return (EmailAddressBo)super.setContactType(contactType);
    }

    @Override
    public EmailAddressBo setPrimary(boolean primary) {
        return (EmailAddressBo)super.setPrimary(primary);
    }

    @Override
    public EmailAddressBo setId(Long id) {
        return (EmailAddressBo)super.setId(id);
    }

    @Override
    public EmailAddressBo setTenantId(Long tenantId) {
        return (EmailAddressBo)super.setTenantId(tenantId);
    }

    @Override
    public EmailAddressBo setActiveFromDate(DateTime activeFromDate) {
        return (EmailAddressBo)super.setActiveFromDate(activeFromDate);
    }

    @Override
    public EmailAddressBo setActiveToDate(DateTime activeToDate) {
        return (EmailAddressBo)super.setActiveToDate(activeToDate);
    }

    @Override
    public EmailAddressBo setLastUpdateDate(DateTime lastUpdateDate) {
        return (EmailAddressBo)super.setLastUpdateDate(lastUpdateDate);
    }

    @Override
    public EmailAddressBo setCreationDate(DateTime creationDate) {
        return (EmailAddressBo)super.setCreationDate(creationDate);
    }

    @Override
    public EmailAddressBo setVersionNumber(Long versionNumber) {
        return (EmailAddressBo)super.setVersionNumber(versionNumber);
    }
}
