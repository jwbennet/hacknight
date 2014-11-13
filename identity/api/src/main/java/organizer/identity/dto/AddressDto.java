package organizer.identity.dto;

import organizer.identity.mo.AddressType;
import organizer.identity.mo.Address;
import organizer.identity.mo.ContactType;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {
        AddressDto.Elements.ID,
        AddressDto.Elements.TENANT_ID,
        AddressDto.Elements.CONTACT_TYPE,
        AddressDto.Elements.CONTACT_ID,
        AddressDto.Elements.TYPE,
        AddressDto.Elements.ADDRESS_LINE_1,
        AddressDto.Elements.ADDRESS_LINE_2,
        AddressDto.Elements.ADDRESS_LINE_3,
        AddressDto.Elements.CITY,
        AddressDto.Elements.STATE,
        AddressDto.Elements.POSTAL_CODE,
        AddressDto.Elements.COUNTRY,
        AddressDto.Elements.PRIMARY,
        AddressDto.Elements.ACTIVE,
        AddressDto.Elements.ACTIVE_FROM_DATE,
        AddressDto.Elements.ACTIVE_TO_DATE,
        AddressDto.Elements.LAST_UPDATE_DATE,
        AddressDto.Elements.CREATION_DATE,
        AddressDto.Elements.OPEN_CONTENT
})
@ApiModel(value = "An address for a contact")
public class AddressDto extends ContactInformationDto<Address> implements Address {

    @XmlElement(name = Elements.TYPE)
    @ApiModelProperty(value = "The type of the address.  Either Home (HOME), Work (WORK), or Other (OTH).", required = true)
    private final AddressType type;
    @XmlElement(name = Elements.ADDRESS_LINE_1)
    @ApiModelProperty(value = "First line of the address", required = true)
    private final String addressLine1;
    @XmlElement(name = Elements.ADDRESS_LINE_2)
    @ApiModelProperty(value = "Second line of the address")
    private final String addressLine2;
    @XmlElement(name = Elements.ADDRESS_LINE_3)
    @ApiModelProperty(value = "Third line of the address")
    private final String addressLine3;
    @XmlElement(name = Elements.CITY)
    @ApiModelProperty(value = "City", required = true)
    private final String city;
    @XmlElement(name = Elements.STATE)
    @ApiModelProperty(value = "The two digit state code", required = true)
    private final String state;
    @XmlElement(name = Elements.POSTAL_CODE)
    @ApiModelProperty(value = "Postal code", required = true)
    private final String postalCode;
    @XmlElement(name = Elements.COUNTRY)
    @ApiModelProperty(value = "The two digit country code", required = true)
    private final String country;

    private AddressDto() {
        this.type = null;
        this.addressLine1 = null;
        this.addressLine2 = null;
        this.addressLine3 = null;
        this.city = null;
        this.state = null;
        this.postalCode = null;
        this.country = null;
    }

    private AddressDto(Builder builder) {
        super(builder);
        this.type = builder.getType();
        this.addressLine1 = builder.getAddressLine1();
        this.addressLine2 = builder.getAddressLine2();
        this.addressLine3 = builder.getAddressLine3();
        this.city = builder.getCity();
        this.state = builder.getState();
        this.postalCode = builder.getPostalCode();
        this.country = builder.getCountry();
    }

    @Override
    public Address asModelObject() {
        return this;
    }

    @Override
    public AddressDto.Builder asBuilder() {
        return Builder.create(this);
    }

    @Override
    public AddressType getType() {
        return type;
    }

    @Override
    public String getAddressLine1() {
        return addressLine1;
    }

    @Override
    public String getAddressLine2() {
        return addressLine2;
    }

    @Override
    public String getAddressLine3() {
        return addressLine3;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String getCountry() {
        return country;
    }

    public static class Builder extends ContactInformationDto.Builder<Address> implements Address {

        private AddressType type;
        private String addressLine1;
        private String addressLine2;
        private String addressLine3;
        private String city;
        private String state;
        private String postalCode;
        private String country;

        private Builder() {
            this.type = null;
            this.addressLine1 = null;
            this.addressLine2 = null;
            this.addressLine3 = null;
            this.city = null;
            this.state = null;
            this.postalCode = null;
            this.country = null;
        }

        private Builder(Address address) {
            super(address);
            this.type = address.getType();
            this.addressLine1 = address.getAddressLine1();
            this.addressLine2 = address.getAddressLine2();
            this.addressLine3 = address.getAddressLine3();
            this.city = address.getCity();
            this.state = address.getState();
            this.postalCode = address.getPostalCode();
            this.country = address.getCountry();
        }

        public static Builder create() {
            return new Builder();
        }

        public static Builder create(Address address) {
            return new Builder(address);
        }

        public AddressDto build() {
            return new AddressDto(this);
        }

        @Override
        public AddressType getType() {
            return type;
        }

        public Builder setType(AddressType type) {
            this.type = type;
            return this;
        }

        @Override
        public String getAddressLine1() {
            return addressLine1;
        }

        public Builder setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        @Override
        public String getAddressLine2() {
            return addressLine2;
        }

        public Builder setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        @Override
        public String getAddressLine3() {
            return addressLine3;
        }

        public Builder setAddressLine3(String addressLine3) {
            this.addressLine3 = addressLine3;
            return this;
        }

        @Override
        public String getCity() {
            return city;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        @Override
        public String getState() {
            return state;
        }

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        @Override
        public String getPostalCode() {
            return postalCode;
        }

        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        @Override
        public String getCountry() {
            return country;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        @Override
         public Builder setContactType(ContactType contactType) {
            return (Builder) super.setContactType(contactType);
        }

        @Override
        public Builder setContactId(Long contactId) {
            return (Builder) super.setContactId(contactId);
        }

        @Override
        public Builder setPrimary(boolean primary) {
            return (Builder) super.setPrimary(primary);
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

    public static final class Adapter extends XmlAdapter<AddressDto, Address> {
        @Override
        public Address unmarshal(AddressDto address) throws Exception {
            return address;
        }

        @Override
        public AddressDto marshal(Address address) throws Exception {
            return (AddressDto) address;
        }
    }

    public static final class Elements extends ContactInformationDto.Elements {

        public static final String ADDRESS_LINE_1 = "addressLine1";
        public static final String ADDRESS_LINE_2 = "addressLine2";
        public static final String ADDRESS_LINE_3 = "addressLine3";
        public static final String CITY = "city";
        public static final String STATE = "state";
        public static final String POSTAL_CODE = "postalCode";
        public static final String COUNTRY = "country";
        private Elements() {
            throw new UnsupportedOperationException("Private constructor should never be invoked");
        }
    }

}
