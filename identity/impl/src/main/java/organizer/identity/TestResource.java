package organizer.identity;

import organizer.identity.mo.Person;
import organizer.identity.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Service("testResource")
public class TestResource {

    @Autowired
    private PersonService personService;

    @GET
    @Path("")
    public Person test() {
        return getPersonService().getPersonByUsername("jawbenne");
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
