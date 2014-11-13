package organizer.identity.service;

import organizer.identity.mo.Person;
import organizer.service.ModelObjectService;

public interface PersonService extends ModelObjectService<Person> {

    Person getPersonByUsername(String username);
}
