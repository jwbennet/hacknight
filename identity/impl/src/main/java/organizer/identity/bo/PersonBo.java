package organizer.identity.bo;

import organizer.identity.dao.PersonDao;
import organizer.identity.mo.Person;
import organizer.bo.AbstractBusinessObject;
import organizer.identity.dto.PersonDto;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "people")
@NamedQueries({
        @NamedQuery(name = PersonDao.FIND_BY_USERNAME, query = PersonDao.FIND_BY_USERNAME_QUERY)
})
public class PersonBo extends AbstractBusinessObject<Person> implements Person {

    @Column(name = "username")
    private String username;
    @Column(name = "birthday")
    private DateTime birthday;

    @Override
    public Person asImmutable() {
        return PersonDto.Builder.create(this).build();
    }

    @Override
    public DateTime getBirthday() {
        return birthday;
    }

    public PersonBo setBirthday(DateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public PersonBo setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public PersonBo setId(Long id) {
        return (PersonBo) super.setId(id);
    }

    @Override
    public PersonBo setTenantId(Long tenantId) {
        return (PersonBo) super.setTenantId(tenantId);
    }

    @Override
    public PersonBo setActiveFromDate(DateTime activeFromDate) {
        return (PersonBo) super.setActiveFromDate(activeFromDate);
    }

    @Override
    public PersonBo setActiveToDate(DateTime activeToDate) {
        return (PersonBo) super.setActiveToDate(activeToDate);
    }

    @Override
    public PersonBo setLastUpdateDate(DateTime lastUpdateDate) {
        return (PersonBo) super.setLastUpdateDate(lastUpdateDate);
    }

    @Override
    public PersonBo setCreationDate(DateTime creationDate) {
        return (PersonBo) super.setCreationDate(creationDate);
    }

    @Override
    public PersonBo setVersionNumber(Long versionNumber) {
        return (PersonBo) super.setVersionNumber(versionNumber);
    }
}
