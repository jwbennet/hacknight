package organizer.identity.resource.impl;

import organizer.dto.AbstractDataTransferObject;
import organizer.identity.resource.*;
import organizer.identity.service.*;
import organizer.identity.dto.GroupDto;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.Group;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Service(value = "groupResource")
public class GroupResourceImpl extends AbstractModelObjectResource<Group, GroupDto> implements GroupResource {

    @Autowired
    private GroupService service;
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

    public GroupResourceImpl() {
        super(GroupDto.class);
    }

    @Override
    public Response getAll(int offset, int limit, Boolean active) {
        String alias = getHttpRequest().getParameter("alias");
        ContactType contactType = null;
        Long contactId = null;
        String contactTypeCode = getHttpRequest().getParameter("contactType");
        String contactIdString = getHttpRequest().getParameter("contactId");
        if(StringUtils.isNotBlank(contactTypeCode) && StringUtils.isNumeric(contactIdString)) {
            contactType = ContactType.fromCode(contactTypeCode);
            contactId = Long.parseLong(contactIdString);
        }
        if( StringUtils.isNotBlank(alias)) {
            if(active != null && !active) {
                throw new IllegalArgumentException("Active parameter provided with false value but finding by alias always returns active results");
            }
            Group group = getService().getGroupByAlias(alias);
            URI groupEndpoint = getUriInfo().getBaseUriBuilder().path(getResourceName() + "/" + group.getId()).build();
            return Response.temporaryRedirect(groupEndpoint).build();
        } else if(contactType != null) {
            if(active != null && !active) {
                throw new IllegalArgumentException("Active parameter provided with false value but finding groups by contact ID will always return active results");
            }
            return generateCollectionResponse(getService().getGroupsByContactId(contactType, contactId, offset, limit));
        }
        return super.getAll(offset, limit, active);
    }

    @Override
    public Response create(GroupDto dto) {
        return super.createRecord(dto);
    }

    @Override
    public Response update(Long id, GroupDto dto) {
        return super.updateRecord(id, dto);
    }

    @Override
    protected AbstractDataTransferObject.Builder<Group> decorateEntity(AbstractDataTransferObject.Builder<Group> groupBuilder) {
        groupBuilder = super.decorateEntity(groupBuilder);
        String fields = getHttpRequest().getParameter("fields");
        if(StringUtils.isNotBlank(fields)) {
            for(String field : StringUtils.split(fields, ",")) {
                try {
                    switch ( field ) {
                        case "name":
                            groupBuilder.decorate(getNameService().getPrimaryByContact(ContactType.GROUP, groupBuilder.getId()));
                            continue;
                        case "address":
                            groupBuilder.decorate(getAddressService().getPrimaryByContact(ContactType.GROUP, groupBuilder.getId()));
                            continue;
                        case "emailAddress":
                            groupBuilder.decorate(getEmailAddressService().getPrimaryByContact(ContactType.GROUP, groupBuilder.getId()));
                            continue;
                        case "phoneNumber":
                            groupBuilder.decorate(getPhoneNumberService().getPrimaryByContact(ContactType.GROUP, groupBuilder.getId()));
                            continue;
                        case "skypeHandle":
                            groupBuilder.decorate(getSkypeHandleService().getPrimaryByContact(ContactType.GROUP, groupBuilder.getId()));
                            continue;
                        default:
                    }
                } catch (NoResultException noResultException) {
                    // If no result is found in the database for one of the nested objects continue on without error
                }
            }
        }
        return groupBuilder;
    }

    @Override
    public Response getNames(Long groupId) {
        return getSubResourceResponse(NameResource.RESOURCE_NAME, groupId);
    }

    @Override
    public Response getAddresses(Long groupId) {
        return getSubResourceResponse(AddressResource.RESOURCE_NAME, groupId);
    }

    @Override
    public Response getEmailAddresses(Long groupId) {
        return getSubResourceResponse(EmailAddressResource.RESOURCE_NAME, groupId);
    }

    @Override
    public Response getPhoneNumbers(Long groupId) {
        return getSubResourceResponse(PhoneNumberResource.RESOURCE_NAME, groupId);
    }

    @Override
    public Response getSkypeHandles(Long groupId) {
        return getSubResourceResponse(SkypeHandleResource.RESOURCE_NAME, groupId);
    }

    @Override
    public Response getGroups(Long groupId) {
        return getSubResourceResponse(GroupResource.RESOURCE_NAME, groupId);
    }

    private Response getSubResourceResponse(String resourceName, Long groupId) {
        return Response
                .temporaryRedirect(
                        getUriInfo()
                                .getRequestUriBuilder()
                                .replacePath(getUriInfo().getBaseUri() + resourceName)
                                .queryParam("contactType", ContactType.GROUP.getCode())
                                .queryParam("contactId", groupId)
                                .build()
                ).build();
    }

    @Override
    protected List<Link> getEntityLinks(Long id) {
        List<Link> linkList = super.getEntityLinks(id);
        linkList.add(generateEntityLink(id, NameResource.RESOURCE_NAME, NameResource.RESOURCE_NAME, "Names for group record " + id));
        linkList.add(generateEntityLink(id, AddressResource.RESOURCE_NAME, AddressResource.RESOURCE_NAME, "Addresses for group record " + id));
        linkList.add(generateEntityLink(id, EmailAddressResource.RESOURCE_NAME, EmailAddressResource.RESOURCE_NAME, "Email addresses for group record " + id));
        linkList.add(generateEntityLink(id, PhoneNumberResource.RESOURCE_NAME, PhoneNumberResource.RESOURCE_NAME, "Phone numbers for group record " + id));
        linkList.add(generateEntityLink(id, SkypeHandleResource.RESOURCE_NAME, SkypeHandleResource.RESOURCE_NAME, "Skype handles for group record " + id));
        linkList.add(generateEntityLink(id, GroupResource.RESOURCE_NAME, GroupResource.RESOURCE_NAME, "Groups for group record " + id));
        return linkList;
    }

    @Override
    public String getResourceName() {
        return RESOURCE_NAME;
    }

    @Override
    public GroupService getService() {
        return service;
    }

    public void setService(GroupService service) {
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
