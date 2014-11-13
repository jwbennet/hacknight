package organizer.identity.resource.impl;

import organizer.identity.dto.EmailAddressDto;
import organizer.identity.mo.EmailAddress;
import organizer.identity.resource.EmailAddressResource;
import organizer.identity.service.EmailAddressService;
import organizer.identity.mo.EmailAddressType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

@Service(value = "emailAddressResource")
public class EmailAddressResourceImpl extends AbstractContactInformationResource<EmailAddress, EmailAddressDto> implements EmailAddressResource {

    @Autowired
    private EmailAddressService service;

    public EmailAddressResourceImpl() {
        super(EmailAddressDto.class);
    }

    @Override
    public Response create(EmailAddressDto dto) {
        return super.createRecord(dto);
    }

    @Override
    public Response update(Long id, EmailAddressDto dto) {
        return super.updateRecord(id, dto);
    }

    @Override
    public String getResourceName() {
        return EmailAddressResource.RESOURCE_NAME;
    }

    @Override
    public EmailAddressType getContactInformationTypeByCode(String code) {
        return EmailAddressType.fromCode(code);
    }

    @Override
    public EmailAddressService getService() {
        return service;
    }

    public void setService(EmailAddressService service) {
        this.service = service;
    }
}
