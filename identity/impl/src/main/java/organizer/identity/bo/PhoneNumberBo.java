package organizer.identity.bo;

import organizer.identity.dto.PhoneNumberDto;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.PhoneNumber;
import organizer.identity.mo.PhoneNumberType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "phone_numbers")
public class PhoneNumberBo extends AbstractContactInformationBo<PhoneNumber> implements PhoneNumber {

    @Column(name = "type")
    @Type( type = "org.jadira.usertype.corejava.PersistentEnum",
            parameters = {
                    @Parameter( name = "enumClass", value = "organizer.identity.mo.PhoneNumberType" ),
                    @Parameter( name = "identifierMethod", value = "getCode" ),
                    @Parameter( name = "valueOfMethod", value = "fromCode" )
            } )
    private PhoneNumberType type;
    @Column(name = "number")
    private String phoneNumber;
    @Column(name = "extension")
    private String extension;

    @Override
    public PhoneNumber asImmutable() {
        return PhoneNumberDto.Builder.create(this).build();
    }

    @Override
    public PhoneNumberType getType() {
        return type;
    }

    public PhoneNumberBo setType(PhoneNumberType type) {
        this.type = type;
        return this;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PhoneNumberBo setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public String getExtension() {
        return extension;
    }

    public PhoneNumberBo setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    @Override
    public PhoneNumberBo setContactId(Long contactId) {
        return (PhoneNumberBo)super.setContactId(contactId);
    }

    @Override
    public PhoneNumberBo setContactType(ContactType contactType) {
        return (PhoneNumberBo)super.setContactType(contactType);
    }

    @Override
    public PhoneNumberBo setPrimary(boolean primary) {
        return (PhoneNumberBo)super.setPrimary(primary);
    }

    @Override
    public PhoneNumberBo setId(Long id) {
        return (PhoneNumberBo)super.setId(id);
    }

    @Override
    public PhoneNumberBo setTenantId(Long tenantId) {
        return (PhoneNumberBo)super.setTenantId(tenantId);
    }

    @Override
    public PhoneNumberBo setActiveFromDate(DateTime activeFromDate) {
        return (PhoneNumberBo)super.setActiveFromDate(activeFromDate);
    }

    @Override
    public PhoneNumberBo setActiveToDate(DateTime activeToDate) {
        return (PhoneNumberBo)super.setActiveToDate(activeToDate);
    }

    @Override
    public PhoneNumberBo setLastUpdateDate(DateTime lastUpdateDate) {
        return (PhoneNumberBo)super.setLastUpdateDate(lastUpdateDate);
    }

    @Override
    public PhoneNumberBo setCreationDate(DateTime creationDate) {
        return (PhoneNumberBo)super.setCreationDate(creationDate);
    }

    @Override
    public PhoneNumberBo setVersionNumber(Long versionNumber) {
        return (PhoneNumberBo)super.setVersionNumber(versionNumber);
    }
}
