package organizer.identity.bo;

import organizer.identity.mo.ContactInformation;
import organizer.bo.AbstractBusinessObject;
import organizer.identity.mo.ContactType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractContactInformationBo<M extends ContactInformation> extends AbstractBusinessObject<M> implements ContactInformation {

    @Column(name = "primary_ind")
    @Type(type = "yes_no")
    private boolean primary;
    @Column( name = "contact_type" )
    @Type( type = "org.jadira.usertype.corejava.PersistentEnum",
            parameters = {
                    @Parameter( name = "enumClass", value = "organizer.identity.mo.ContactType" ),
                    @Parameter( name = "identifierMethod", value = "getCode" ),
                    @Parameter( name = "valueOfMethod", value = "fromCode" )
            } )
    private ContactType contactType;
    @Column(name = "contact_id")
    private Long contactId;

    @Override
    public ContactType getContactType() {
        return contactType;
    }

    @Override
    public Long getContactId() {
        return contactId;
    }

    public AbstractContactInformationBo<M> setContactId(Long contactId) {
        this.contactId = contactId;
        return this;
    }

    public AbstractContactInformationBo<M> setContactType(ContactType contactType) {
        this.contactType = contactType;
        return this;
    }

    @Override
    public boolean isPrimary() {
        return primary;
    }

    public AbstractContactInformationBo<M> setPrimary(boolean primary) {
        this.primary = primary;
        return this;
    }

    @Override
    public AbstractContactInformationBo<M> setId(Long id) {
        return (AbstractContactInformationBo<M>) super.setId(id);
    }

    @Override
    public AbstractContactInformationBo<M> setTenantId(Long tenantId) {
        return (AbstractContactInformationBo<M>) super.setTenantId(tenantId);
    }

    @Override
    public AbstractContactInformationBo<M> setActiveFromDate(DateTime activeFromDate) {
        return (AbstractContactInformationBo<M>) super.setActiveFromDate(activeFromDate);
    }

    @Override
    public AbstractContactInformationBo<M> setActiveToDate(DateTime activeToDate) {
        return (AbstractContactInformationBo<M>) super.setActiveToDate(activeToDate);
    }

    @Override
    public AbstractContactInformationBo<M> setLastUpdateDate(DateTime lastUpdateDate) {
        return (AbstractContactInformationBo<M>) super.setLastUpdateDate(lastUpdateDate);
    }

    @Override
    public AbstractContactInformationBo<M> setCreationDate(DateTime creationDate) {
        return (AbstractContactInformationBo<M>) super.setCreationDate(creationDate);
    }

    @Override
    public AbstractContactInformationBo<M> setVersionNumber(Long versionNumber) {
        return (AbstractContactInformationBo<M>) super.setVersionNumber(versionNumber);
    }
}
