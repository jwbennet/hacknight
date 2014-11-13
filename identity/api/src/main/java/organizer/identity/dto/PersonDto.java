package organizer.identity.dto;

import organizer.dto.AbstractDataTransferObject;
import organizer.identity.mo.Person;
import organizer.adapters.DateTimeAdapter;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {
        PersonDto.Elements.ID,
        PersonDto.Elements.TENANT_ID,
        PersonDto.Elements.USERNAME,
        PersonDto.Elements.BIRTHDAY,
        PersonDto.Elements.ACTIVE,
        PersonDto.Elements.ACTIVE_FROM_DATE,
        PersonDto.Elements.ACTIVE_TO_DATE,
        PersonDto.Elements.LAST_UPDATE_DATE,
        PersonDto.Elements.CREATION_DATE,
        PersonDto.Elements.OPEN_CONTENT
})
@ApiModel(value = "A person is a unique individual")
public class PersonDto extends AbstractDataTransferObject<Person> implements Person {

    @XmlElement(name = Elements.USERNAME)
    @ApiModelProperty(value = "A unique, short identifier for a person.  Must be between 6 and 40 characters in length.", required=true)
    private final String username;

    @XmlElement(name = Elements.BIRTHDAY)
    @XmlJavaTypeAdapter(type = DateTime.class, value = DateTimeAdapter.class)
    @ApiModelProperty(value = "The birthday of the person. Must be a date in the past in ISO-8601 format (YYYY-MM-DD).")
    private final DateTime birthday;

    private PersonDto() {
        this.username = null;
        this.birthday = null;
    }

    private PersonDto(Builder builder) {
        super(builder);
        this.username = builder.getUsername();
        this.birthday = builder.getBirthday();
    }

    @Override
    public DateTime getBirthday() {
        return birthday;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Person asModelObject() {
        return this;
    }

    @Override
    public PersonDto.Builder asBuilder() {
        return Builder.create(this);
    }

    public static class Builder extends AbstractDataTransferObject.Builder<Person> implements Person {

        private String username;
        private DateTime birthday;

        private Builder() {
            this.username = null;
            this.birthday = null;
        }

        private Builder(Person person) {
            super(person);
            this.username = person.getUsername();
            this.birthday = person.getBirthday();
        }

        public static Builder create() {
            return new Builder();
        }

        public static Builder create(Person person) {
            return new Builder(person);
        }

        public PersonDto build() {
            return new PersonDto(this);
        }

        @Override
        public DateTime getBirthday() {
            return birthday;
        }

        public Builder setBirthday(DateTime birthday) {
            this.birthday = birthday;
            return this;
        }

        @Override
        public String getUsername() {
            return username;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
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

    public static final class Adapter extends XmlAdapter<PersonDto, Person> {
        @Override
        public Person unmarshal(PersonDto person) throws Exception {
            return person;
        }

        @Override
        public PersonDto marshal(Person person) throws Exception {
            return (PersonDto) person;
        }
    }

    public static final class Elements extends AbstractDataTransferObject.Elements {

        public static final String USERNAME = "username";
        public static final String BIRTHDAY = "birthday";

        private Elements() {
            throw new UnsupportedOperationException("Private constructor should never be invoked");
        }
    }
}
