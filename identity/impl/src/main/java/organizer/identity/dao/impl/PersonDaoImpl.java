package organizer.identity.dao.impl;

import organizer.dao.impl.AbstractBusinessObjectDao;
import organizer.identity.bo.PersonBo;
import organizer.identity.dao.PersonDao;
import organizer.identity.mo.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl extends AbstractBusinessObjectDao<Person, PersonBo> implements PersonDao {

    public PersonDaoImpl() {
        super(PersonBo.class);
    }

    @Override
    public PersonBo getPersonByUsername(String username) {
        return getEntityManager()
                .createNamedQuery(PersonDao.FIND_BY_USERNAME, PersonBo.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
