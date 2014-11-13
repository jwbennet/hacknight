package organizer.identity.bo;

import organizer.identity.mo.ContactType;
import organizer.identity.mo.Name;
import organizer.identity.dto.NameDto;
import organizer.identity.mo.NameType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "names")
public class NameBo extends AbstractContactInformationBo<Name> implements Name {

    @Column(name = "type")
    @Type( type = "org.jadira.usertype.corejava.PersistentEnum",
            parameters = {
                    @Parameter( name = "enumClass", value = "organizer.identity.mo.NameType" ),
                    @Parameter( name = "identifierMethod", value = "getCode" ),
                    @Parameter( name = "valueOfMethod", value = "fromCode" )
            } )
    private NameType type;
    @Column(name = "given_name")
    private String givenName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "surname")
    private String surname;
    @Column(name = "suffix_name")
    private String suffix;

    @Override
    public Name asImmutable() {
        return NameDto.Builder.create(this).build();
    }

    @Override
    public NameType getType() {
        return type;
    }

    public NameBo setType(NameType type) {
        this.type = type;
        return this;
    }

    @Override
    public String getGivenName() {
        return givenName;
    }

    public NameBo setGivenName(String givenName) {
        this.givenName = givenName;
        return this;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    public NameBo setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    public NameBo setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    @Override
    public String getSuffix() {
        return suffix;
    }

    public NameBo setSuffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    @Override
    public NameBo setContactId(Long contactId) {
        return (NameBo)super.setContactId(contactId);
    }

    @Override
    public NameBo setContactType(ContactType contactType) {
        return (NameBo)super.setContactType(contactType);
    }

    @Override
    public NameBo setPrimary(boolean primary) {
        return (NameBo)super.setPrimary(primary);
    }

    @Override
    public NameBo setId(Long id) {
        return (NameBo)super.setId(id);
    }

    @Override
    public NameBo setTenantId(Long tenantId) {
        return (NameBo)super.setTenantId(tenantId);
    }

    @Override
    public NameBo setActiveFromDate(DateTime activeFromDate) {
        return (NameBo)super.setActiveFromDate(activeFromDate);
    }

    @Override
    public NameBo setActiveToDate(DateTime activeToDate) {
        return (NameBo)super.setActiveToDate(activeToDate);
    }

    @Override
    public NameBo setLastUpdateDate(DateTime lastUpdateDate) {
        return (NameBo)super.setLastUpdateDate(lastUpdateDate);
    }

    @Override
    public NameBo setCreationDate(DateTime creationDate) {
        return (NameBo)super.setCreationDate(creationDate);
    }

    @Override
    public NameBo setVersionNumber(Long versionNumber) {
        return (NameBo)super.setVersionNumber(versionNumber);
    }
}
