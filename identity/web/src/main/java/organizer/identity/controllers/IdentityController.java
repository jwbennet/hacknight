package organizer.identity.controllers;

import organizer.identity.dao.GroupMemberDao;
import organizer.identity.mo.*;
import organizer.identity.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping( "/*" )
public class IdentityController {

    private static final Logger LOG = LoggerFactory.getLogger(IdentityController.class);

    @Autowired
    private PersonService personService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private EmailAddressService emailAddressService;
    @Autowired
    private PhoneNumberService phoneNumberService;
    @Autowired
    private GroupMemberDao groupMemberDao;
    @Autowired
    private GroupService groupService;

    @RequestMapping( "" )
    public ModelAndView index() {
        Person person = personService.getPersonByUsername("jawbenne");
        LOG.info("Person for jawbenne = " + person);
        Address address = addressService.getPrimaryByContact(ContactType.PERSON, person.getId());
        LOG.info("  Address for jawbenne = " + address);
        EmailAddress emailAddress = emailAddressService.getPrimaryByContact(ContactType.PERSON, person.getId());
        LOG.info("  Email Address for jawbenne = " + emailAddress);
        PhoneNumber phoneNumber = phoneNumberService.getPrimaryByContact(ContactType.PERSON, person.getId());
        LOG.info("  Phone Number for jawbenne = " + phoneNumber);

        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }
}
