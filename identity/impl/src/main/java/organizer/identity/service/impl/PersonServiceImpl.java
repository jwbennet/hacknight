package organizer.identity.service.impl;

import organizer.identity.bo.PersonBo;
import organizer.identity.dao.PersonDao;
import organizer.identity.mo.Person;
import organizer.service.impl.AbstractModelObjectService;
import organizer.identity.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "personService")
public class PersonServiceImpl extends AbstractModelObjectService<Person, PersonBo> implements PersonService {

    @Autowired
    private PersonDao dao;

    public PersonServiceImpl() {
        super(PersonBo.class);
    }

    @Override
    public Person getPersonByUsername(String username) {
        return getDao().getPersonByUsername(username).asImmutable();
    }

    @Override
    protected PersonBo prepareForCreate(Person mo) {
        return super.prepareForCreate(mo)
                .setUsername(mo.getUsername())
                .setBirthday(mo.getBirthday());
    }

    @Override
    protected PersonBo prepareForUpdate(PersonBo existingBo, Person mo) {
        return super.prepareForUpdate(existingBo, mo)
                .setUsername(mo.getUsername())
                .setBirthday(mo.getBirthday());
    }

    @Override
    protected PersonDao getDao() {
        return dao;
    }

    public void setDao(PersonDao dao) {
        this.dao = dao;
    }
}
