package organizer.identity.dao;

import organizer.identity.mo.Person;
import organizer.dao.BusinessObjectDao;
import organizer.identity.bo.PersonBo;

public interface PersonDao extends BusinessObjectDao<Person, PersonBo> {

    public static final String FIND_BY_USERNAME = "Person.findByUsername";
    public static final String FIND_BY_USERNAME_QUERY = "SELECT p FROM PersonBo p WHERE p.username = :username AND (p.activeFromDate IS NULL OR p.activeFromDate < CURRENT_TIMESTAMP) AND (p.activeToDate IS NULL OR p.activeToDate > CURRENT_TIMESTAMP)";

    PersonBo getPersonByUsername(String username);
}
