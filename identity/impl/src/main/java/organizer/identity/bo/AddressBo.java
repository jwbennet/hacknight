package organizer.identity.bo;

import organizer.identity.mo.AddressType;
import organizer.identity.dto.AddressDto;
import organizer.identity.mo.Address;
import organizer.identity.mo.ContactType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class AddressBo extends AbstractContactInformationBo<Address> implements Address {

    @Column(name = "type")
    @Type( type = "org.jadira.usertype.corejava.PersistentEnum",
            parameters = {
                    @Parameter( name = "enumClass", value = "organizer.identity.mo.AddressType" ),
                    @Parameter( name = "identifierMethod", value = "getCode" ),
                    @Parameter( name = "valueOfMethod", value = "fromCode" )
            } )
    private AddressType type;
    @Column(name = "line1")
    private String addressLine1;
    @Column(name = "line2")
    private String addressLine2;
    @Column(name = "line3")
    private String addressLine3;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "country")
    private String country;

    @Override
    public Address asImmutable() {
        return AddressDto.Builder.create(this).build();
    }

    @Override
    public AddressType getType() {
        return type;
    }

    public AddressBo setType(AddressType type) {
        this.type = type;
        return this;
    }

    @Override
    public String getAddressLine1() {
        return addressLine1;
    }

    public AddressBo setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    @Override
    public String getAddressLine2() {
        return addressLine2;
    }

    public AddressBo setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    @Override
    public String getAddressLine3() {
        return addressLine3;
    }

    public AddressBo setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
        return this;
    }

    @Override
    public String getCity() {
        return city;
    }

    public AddressBo setCity(String city) {
        this.city = city;
        return this;
    }

    @Override
    public String getState() {
        return state;
    }

    public AddressBo setState(String state) {
        this.state = state;
        return this;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    public AddressBo setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AddressBo setCountry(String country) {
        this.country = country;
        return this;
    }

    @Override
    public AddressBo setContactId(Long contactId) {
        return (AddressBo) super.setContactId(contactId);
    }

    @Override
    public AddressBo setContactType(ContactType contactType) {
        return (AddressBo) super.setContactType(contactType);
    }

    @Override
    public AddressBo setPrimary(boolean primary) {
        super.setPrimary(primary);
        return this;
    }

    @Override
    public AddressBo setId(Long id) {
        return (AddressBo) super.setId(id);
    }

    @Override
    public AddressBo setTenantId(Long tenantId) {
        return (AddressBo) super.setTenantId(tenantId);
    }

    @Override
    public AddressBo setActiveFromDate(DateTime activeFromDate) {
        return (AddressBo) super.setActiveFromDate(activeFromDate);
    }

    @Override
    public AddressBo setActiveToDate(DateTime activeToDate) {
        return (AddressBo) super.setActiveToDate(activeToDate);
    }

    @Override
    public AddressBo setLastUpdateDate(DateTime lastUpdateDate) {
        return (AddressBo) super.setLastUpdateDate(lastUpdateDate);
    }

    @Override
    public AddressBo setCreationDate(DateTime creationDate) {
        return (AddressBo) super.setCreationDate(creationDate);
    }

    @Override
    public AddressBo setVersionNumber(Long versionNumber) {
        return (AddressBo) super.setVersionNumber(versionNumber);
    }
}
