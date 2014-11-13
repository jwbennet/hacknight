package organizer.identity.resource.impl;

import organizer.dto.AbstractDataTransferObject;
import organizer.identity.dto.PersonDto;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.Person;
import organizer.identity.resource.*;
import organizer.identity.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Service(value = "personResource")
public class PersonResourceImpl extends AbstractModelObjectResource<Person, PersonDto> implements PersonResource {

    @Autowired
    private PersonService service;
    @Autowired
    private NameService nameService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private EmailAddressService emailAddressService;
    @Autowired
    private PhoneNumberService phoneNumberService;
    @Autowired
    private SkypeHandleService skypeHandleService;

    public PersonResourceImpl() {
        super(PersonDto.class);
    }

    @Override
    public Response getAll(int offset, int limit, Boolean active) {
        String username = getHttpRequest().getParameter("username");
        if( StringUtils.isNotBlank(username)) {
            if(active != null && !active) {
                throw new IllegalArgumentException("Active parameter provided with false value but finding by username always returns active results");
            }
            Person person = getService().getPersonByUsername(username);
            URI personEndpoint = getUriInfo().getBaseUriBuilder().path(getResourceName() + "/" + person.getId()).build();
            return Response.temporaryRedirect(personEndpoint).build();
        }
        return super.getAll(offset, limit, active);
    }

    @Override
    public Response create(PersonDto dto) {
        return super.createRecord(dto);
    }

    @Override
    public Response update(Long id, PersonDto dto) {
        return super.updateRecord(id, dto);
    }

    @Override
    protected AbstractDataTransferObject.Builder<Person> decorateEntity(AbstractDataTransferObject.Builder<Person> personBuilder) {
        personBuilder = super.decorateEntity(personBuilder);
        String fields = getHttpRequest().getParameter("fields");
        if(StringUtils.isNotBlank(fields)) {
            for(String field : StringUtils.split(fields, ",")) {
                try {
                    switch ( field ) {
                        case "name":
                            personBuilder.decorate(getNameService().getPrimaryByContact(ContactType.PERSON, personBuilder.getId()));
                            continue;
                        case "address":
                            personBuilder.decorate(getAddressService().getPrimaryByContact(ContactType.PERSON, personBuilder.getId()));
                            continue;
                        case "emailAddress":
                            personBuilder.decorate(getEmailAddressService().getPrimaryByContact(ContactType.PERSON, personBuilder.getId()));
                            continue;
                        case "phoneNumber":
                            personBuilder.decorate(getPhoneNumberService().getPrimaryByContact(ContactType.PERSON, personBuilder.getId()));
                            continue;
                        case "skypeHandle":
                            personBuilder.decorate(getSkypeHandleService().getPrimaryByContact(ContactType.PERSON, personBuilder.getId()));
                            continue;
                        default:
                    }
                } catch (NoResultException noResultException) {
                    // If no result is found in the database for one of the nested objects continue on without error
                }
            }
        }
        return personBuilder;
    }

    @Override
    public Response getNames(Long personId) {
        return getSubResourceResponse(NameResource.RESOURCE_NAME, personId);
    }

    @Override
    public Response getAddresses(Long personId) {
        return getSubResourceResponse(AddressResource.RESOURCE_NAME, personId);
    }

    @Override
    public Response getEmailAddresses(Long personId) {
        return getSubResourceResponse(EmailAddressResource.RESOURCE_NAME, personId);
    }

    @Override
    public Response getPhoneNumbers(Long personId) {
        return getSubResourceResponse(PhoneNumberResource.RESOURCE_NAME, personId);
    }

    @Override
    public Response getSkypeHandles(Long personId) {
        return getSubResourceResponse(SkypeHandleResource.RESOURCE_NAME, personId);
    }

    @Override
    public Response getGroups(Long personId) {
        return getSubResourceResponse(GroupResource.RESOURCE_NAME, personId);
    }

    private Response getSubResourceResponse(String resourceName, Long personId) {
        return Response
                .temporaryRedirect(
                        getUriInfo()
                                .getRequestUriBuilder()
                                .replacePath(getUriInfo().getBaseUri() + resourceName)
                                .queryParam("contactType", ContactType.PERSON.getCode())
                                .queryParam("contactId", personId)
                                .build()
                ).build();
    }

    @Override
    protected List<Link> getEntityLinks(Long id) {
        List<Link> linkList = super.getEntityLinks(id);
        linkList.add(generateEntityLink(id, NameResource.RESOURCE_NAME, NameResource.RESOURCE_NAME, "Names for person record " + id));
        linkList.add(generateEntityLink(id, AddressResource.RESOURCE_NAME, AddressResource.RESOURCE_NAME, "Addresses for person record " + id));
        linkList.add(generateEntityLink(id, EmailAddressResource.RESOURCE_NAME, EmailAddressResource.RESOURCE_NAME, "Email addresses for person record " + id));
        linkList.add(generateEntityLink(id, PhoneNumberResource.RESOURCE_NAME, PhoneNumberResource.RESOURCE_NAME, "Phone numbers for person record " + id));
        linkList.add(generateEntityLink(id, SkypeHandleResource.RESOURCE_NAME, SkypeHandleResource.RESOURCE_NAME, "Skype handles for person record " + id));
        linkList.add(generateEntityLink(id, GroupResource.RESOURCE_NAME, GroupResource.RESOURCE_NAME, "Groups for person record " + id));
        return linkList;
    }

    @Override
    public String getResourceName() {
        return RESOURCE_NAME;
    }

    @Override
    public PersonService getService() {
        return service;
    }

    public void setService(PersonService service) {
        this.service = service;
    }

    public NameService getNameService() {
        return nameService;
    }

    public void setNameService(NameService nameService) {
        this.nameService = nameService;
    }

    public AddressService getAddressService() {
        return addressService;
    }

    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    public EmailAddressService getEmailAddressService() {
        return emailAddressService;
    }

    public void setEmailAddressService(EmailAddressService emailAddressService) {
        this.emailAddressService = emailAddressService;
    }

    public PhoneNumberService getPhoneNumberService() {
        return phoneNumberService;
    }

    public void setPhoneNumberService(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    public SkypeHandleService getSkypeHandleService() {
        return skypeHandleService;
    }

    public void setSkypeHandleService(SkypeHandleService skypeHandleService) {
        this.skypeHandleService = skypeHandleService;
    }
}
