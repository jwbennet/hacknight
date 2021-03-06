package organizer.dto;

import organizer.adapters.DateTimeAdapter;
import organizer.mo.ModelObject;
import organizer.util.InactivatableDateRangeUtils;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.*;

@XmlTransient
public abstract class AbstractDataTransferObject<M extends ModelObject> implements ModelObject {

    private static final Collection<String> EXCLUDED_FIELDS = Arrays.asList("_hashCode", "_toString", "_openContent", "_openJsonContent");

    private transient volatile Integer _hashCode;
    private transient volatile String _toString;

    @XmlElement(name = Elements.ID)
    @ApiModelProperty(value = "The generated primary key for this object.  This is generated by the database and will be ignored if provided in create operations.  If it is provided with an update operation and it differs from the original database value an exception will be thrown.")
    private final Long id;
    @XmlElement(name = Elements.TENANT_ID)
    @ApiModelProperty(value = "The identifier for tenant which owns this object", required = true)
    private final Long tenantId;
    @XmlElement(name = Elements.ACTIVE_FROM_DATE)
    @XmlJavaTypeAdapter(type = DateTime.class, value = DateTimeAdapter.class)
    @ApiModelProperty(value = "The date/time when this object starts being active.  If null the object will be active from the beginning of time.  Must be a date/time in ISO-8601 format (YYYY-MM-DDTHH24:MM:SS).")
    private final DateTime activeFromDate;
    @XmlElement(name = Elements.ACTIVE_TO_DATE)
    @XmlJavaTypeAdapter(type = DateTime.class, value = DateTimeAdapter.class)
    @ApiModelProperty(value = "The date/time when this object is no longer active.  If null the object will be active until the end of time.  Must be a date/time in ISO-8601 format (YYYY-MM-DDTHH24:MM:SS).")
    private final DateTime activeToDate;
    @XmlElement(name = Elements.LAST_UPDATE_DATE)
    @XmlJavaTypeAdapter(type = DateTime.class, value = DateTimeAdapter.class)
    @ApiModelProperty(value = "The date/time when this object was last updated in the database.  If it is provided to create/update operations it will be ignored.")
    private final DateTime lastUpdateDate;
    @XmlElement(name = Elements.CREATION_DATE)
    @XmlJavaTypeAdapter(type = DateTime.class, value = DateTimeAdapter.class)
    @ApiModelProperty(value = "The date/time when this object was created in the database.  If it is provided to create/update operations it will be ignored.")
    private final DateTime creationDate;
    @XmlAnyElement
    @JsonIgnore
    private final List<Object> _openContent;
    @XmlTransient
    private final Map<String, Object> _openJsonContent;

    protected AbstractDataTransferObject() {
        this.id = null;
        this.tenantId = null;
        this.activeFromDate = null;
        this.activeToDate = null;
        this.lastUpdateDate = null;
        this.creationDate = null;
        this._openContent = null;
        this._openJsonContent = null;
    }

    protected AbstractDataTransferObject(Builder builder) {
        this.id = builder.getId();
        this.tenantId = builder.getTenantId();
        this.activeFromDate = builder.getActiveFromDate();
        this.activeToDate = builder.getActiveToDate();
        this.lastUpdateDate = builder.getLastUpdateDate();
        this.creationDate = builder.getCreationDate();
        this._openContent = builder.getOpenContent();
        this._openJsonContent = builder.getOpenJsonContent();
    }

    public abstract M asModelObject();

    public abstract Builder<M> asBuilder();

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(obj, this, EXCLUDED_FIELDS);
    }

    @Override
    public int hashCode() {
        //using DCL idiom to cache hashCodes.  Hashcodes on immutable objects never change.  They can be safely cached.
        //see effective java 2nd ed. pg. 71
        Integer hashCode = _hashCode;
        if (hashCode == null) {
            synchronized (this) {
                hashCode = _hashCode;
                if (hashCode == null) {
                    _hashCode = hashCode = HashCodeBuilder.reflectionHashCode(this, EXCLUDED_FIELDS);
                }
            }
        }

        return hashCode;
    }

    @Override
    public String toString() {
        //using DCL idiom to cache toString.  toStrings on immutable objects never change.  They can be safely cached.
        //see effective java 2nd ed. pg. 71
        String value = _toString;
        if (value == null) {
            synchronized (this) {
                value = _toString;
                if (value == null) {
                    _toString = value = ToStringBuilder.reflectionToString(this);
                }
            }
        }

        return value;
    }

    @Override
    @XmlElement(name = Elements.ACTIVE)
    @ApiModelProperty(value = "This is a calculated value to assist with determining if an object is currently active.  If it is provided to create/update operations it will be ignored.")
    public boolean isActive() {
        return InactivatableDateRangeUtils.isActive(getActiveFromDate(), getActiveToDate(), null);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getTenantId() {
        return tenantId;
    }

    @Override
    public DateTime getActiveFromDate() {
        return activeFromDate;
    }

    @Override
    public DateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public DateTime getActiveToDate() {
        return activeToDate;
    }

    @Override
    public DateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public List<Object> get_openContent() {
        return _openContent;
    }

    @JsonAnyGetter
    public Map<String, Object> get_openJsonContent() {
        return _openJsonContent;
    }

    public abstract static class Builder<M extends ModelObject> implements ModelObject {

        private static final long serialVersionUID = 6154861994158612797L;

        private Long id;
        private Long tenantId;
        private DateTime activeFromDate;
        private DateTime activeToDate;
        private DateTime lastUpdateDate;
        private DateTime creationDate;
        private List<Object> openContent;
        private Map<String, Object> openJsonContent;

        protected Builder() {
            this.id = null;
            this.tenantId = null;
            this.activeFromDate = null;
            this.activeToDate = null;
            this.lastUpdateDate = null;
            this.creationDate = null;
            this.openContent = null;
        }

        protected Builder(ModelObject modelObject) {
            this.id = modelObject.getId();
            this.tenantId = modelObject.getTenantId();
            this.activeFromDate = modelObject.getActiveFromDate();
            this.activeToDate = modelObject.getActiveToDate();
            this.lastUpdateDate = modelObject.getLastUpdateDate();
            this.creationDate = modelObject.getCreationDate();
            this.openContent = null;
        }

        public abstract M build();

        @Override
        public Long getId() {
            return id;
        }

        public Builder<M> setId(Long id) {
            this.id = id;
            return this;
        }

        @Override
        public Long getTenantId() {
            return tenantId;
        }

        public Builder<M> setTenantId(Long tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        @Override
        public DateTime getActiveFromDate() {
            return activeFromDate;
        }

        public Builder<M> setActiveFromDate(DateTime activeFromDate) {
            this.activeFromDate = activeFromDate;
            return this;
        }

        @Override
        public DateTime getActiveToDate() {
            return activeToDate;
        }

        public Builder<M> setActiveToDate(DateTime activeToDate) {
            this.activeToDate = activeToDate;
            return this;
        }

        @Override
        public DateTime getLastUpdateDate() {
            return lastUpdateDate;
        }

        public Builder<M> setLastUpdateDate(DateTime lastUpdateDate) {
            this.lastUpdateDate = lastUpdateDate;
            return this;
        }

        @Override
        public DateTime getCreationDate() {
            return creationDate;
        }

        public Builder<M> setCreationDate(DateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public List<Object> getOpenContent() {
            return openContent;
        }

        public Map<String, Object> getOpenJsonContent() {
            return openJsonContent;
        }

        public Builder<M> decorate(ModelObject modelObject) {
            if(this.openContent == null) {
                this.openContent = new ArrayList<>();
                this.openJsonContent = new HashMap<>();
            }
            String name = modelObject.getClass().getAnnotation(XmlRootElement.class).name();
            if(StringUtils.isNotBlank(name)) {
                this.openContent.add(modelObject);
                this.openJsonContent.put(name, modelObject);
            }
            return this;
        }

        public Builder<M> decorate(Decoratable decoration) {
            if(this.openContent == null) {
                this.openContent = new ArrayList<>();
                this.openJsonContent = new HashMap<>();
            }
            this.openContent.add(decoration);
            this.openJsonContent.put(decoration.getClass().getAnnotation(XmlRootElement.class).name(), decoration.getJsonRepresentation());
            return this;
        }

        public boolean isActive() {
            throw new UnsupportedOperationException("This is a derived property and is invalid on this class");
        }

        public boolean isActive(DateTime time) {
            throw new UnsupportedOperationException("This is a derived property and is invalid on this class");
        }
    }

    public static class Elements {

        public static final String ACTIVE = "active";
        public static final String ACTIVE_FROM_DATE = "activeFromDate";
        public static final String ACTIVE_TO_DATE = "activeToDate";
        public static final String CREATION_DATE = "creationDate";
        public static final String ID = "id";
        public static final String LAST_UPDATE_DATE = "lastUpdateDate";
        public static final String TENANT_ID = "tenantId";
        public static final String OPEN_CONTENT = "_openContent";

        protected Elements() {
            throw new UnsupportedOperationException("Private constructor should never be invoked");
        }
    }

    @Override
    public boolean isActive(DateTime activeAsOfDate) {
        return InactivatableDateRangeUtils.isActive(getActiveFromDate(), getActiveToDate(), activeAsOfDate);
    }




}
