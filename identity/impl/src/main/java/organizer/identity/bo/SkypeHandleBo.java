package organizer.identity.bo;

import organizer.identity.dto.SkypeHandleDto;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.SkypeHandle;
import organizer.identity.mo.SkypeHandleType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "skype_handles")
public class SkypeHandleBo extends AbstractContactInformationBo<SkypeHandle> implements SkypeHandle {

    @Column(name = "type")
    @Type( type = "org.jadira.usertype.corejava.PersistentEnum",
            parameters = {
                    @Parameter( name = "enumClass", value = "organizer.identity.mo.SkypeHandleType" ),
                    @Parameter( name = "identifierMethod", value = "getCode" ),
                    @Parameter( name = "valueOfMethod", value = "fromCode" )
            } )
    private SkypeHandleType type;
    @Column(name = "username")
    private String username;

    @Override
    public SkypeHandle asImmutable() {
        return SkypeHandleDto.Builder.create(this).build();
    }

    @Override
    public SkypeHandleType getType() {
        return type;
    }

    public SkypeHandleBo setType(SkypeHandleType type) {
        this.type = type;
        return this;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public SkypeHandleBo setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public SkypeHandleBo setContactId(Long contactId) {
        return (SkypeHandleBo)super.setContactId(contactId);
    }

    @Override
    public SkypeHandleBo setContactType(ContactType contactType) {
        return (SkypeHandleBo)super.setContactType(contactType);
    }

    @Override
    public SkypeHandleBo setPrimary(boolean primary) {
        return (SkypeHandleBo)super.setPrimary(primary);
    }

    @Override
    public SkypeHandleBo setId(Long id) {
        return (SkypeHandleBo)super.setId(id);
    }

    @Override
    public SkypeHandleBo setTenantId(Long tenantId) {
        return (SkypeHandleBo)super.setTenantId(tenantId);
    }

    @Override
    public SkypeHandleBo setActiveFromDate(DateTime activeFromDate) {
        return (SkypeHandleBo)super.setActiveFromDate(activeFromDate);
    }

    @Override
    public SkypeHandleBo setActiveToDate(DateTime activeToDate) {
        return (SkypeHandleBo)super.setActiveToDate(activeToDate);
    }

    @Override
    public SkypeHandleBo setLastUpdateDate(DateTime lastUpdateDate) {
        return (SkypeHandleBo)super.setLastUpdateDate(lastUpdateDate);
    }

    @Override
    public SkypeHandleBo setCreationDate(DateTime creationDate) {
        return (SkypeHandleBo)super.setCreationDate(creationDate);
    }

    @Override
    public SkypeHandleBo setVersionNumber(Long versionNumber) {
        return (SkypeHandleBo)super.setVersionNumber(versionNumber);
    }
}
