package organizer.bo;

import organizer.mo.ModelObject;
import organizer.util.InactivatableDateRangeUtils;
import organizer.mo.common.Versioned;
import org.joda.time.DateTime;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractBusinessObject<M extends ModelObject> implements ModelObject, Versioned {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "tenant_id")
    private Long tenantId;
    @Column(name = "active_from_date")
    private DateTime activeFromDate;
    @Column(name = "active_to_date")
    private DateTime activeToDate;
    @Column(name = "update_date")
    private DateTime lastUpdateDate;
    @Column(name = "create_date")
    private DateTime creationDate;
    @Version
    @Column(name = "version")
    private Long versionNumber;

    public abstract M asImmutable();

    @PrePersist
    @PreUpdate
    public void updateTimestamps() {
        DateTime now = DateTime.now();
        this.setLastUpdateDate(now);
        if( this.getCreationDate() == null ) {
            this.setCreationDate(now);
        }
    }

    @Override
    public boolean isActive() {
        return InactivatableDateRangeUtils.isActive(getActiveFromDate(), getActiveToDate(), null);
    }

    @Override
    public boolean isActive(DateTime activeAsOfDate) {
        return InactivatableDateRangeUtils.isActive(getActiveFromDate(), getActiveToDate(), activeAsOfDate);
    }

    @Override
    public Long getId() {
        return id;
    }

    public AbstractBusinessObject<M> setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public Long getTenantId() {
        return tenantId;
    }

    public AbstractBusinessObject<M> setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public DateTime getActiveFromDate() {
        return activeFromDate;
    }

    public AbstractBusinessObject<M> setActiveFromDate(DateTime activeFromDate) {
        this.activeFromDate = activeFromDate;
        return this;
    }

    @Override
    public DateTime getActiveToDate() {
        return activeToDate;
    }

    public AbstractBusinessObject<M> setActiveToDate(DateTime activeToDate) {
        this.activeToDate = activeToDate;
        return this;
    }

    @Override
    public DateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public DateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public AbstractBusinessObject<M> setLastUpdateDate(DateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        return this;
    }

    public AbstractBusinessObject<M> setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    @Override
    public Long getVersionNumber() {
        return versionNumber;
    }

    public AbstractBusinessObject<M> setVersionNumber(Long versionNumber) {
        this.versionNumber = versionNumber;
        return this;
    }
}
