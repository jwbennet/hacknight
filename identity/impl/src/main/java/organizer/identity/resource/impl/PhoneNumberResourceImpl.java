package organizer.identity.resource.impl;

import organizer.identity.dto.PhoneNumberDto;
import organizer.identity.mo.PhoneNumberType;
import organizer.identity.resource.PhoneNumberResource;
import organizer.identity.service.PhoneNumberService;
import organizer.identity.mo.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

@Service(value = "phoneNumberResource")
public class PhoneNumberResourceImpl extends AbstractContactInformationResource<PhoneNumber, PhoneNumberDto> implements PhoneNumberResource {

    @Autowired
    private PhoneNumberService service;

    public PhoneNumberResourceImpl() {
        super(PhoneNumberDto.class);
    }

    @Override
    public Response create(PhoneNumberDto dto) {
        return super.createRecord(dto);
    }

    @Override
    public Response update(Long id, PhoneNumberDto dto) {
        return super.updateRecord(id, dto);
    }

    @Override
    public String getResourceName() {
        return PhoneNumberResource.RESOURCE_NAME;
    }

    @Override
    public PhoneNumberType getContactInformationTypeByCode(String code) {
        return PhoneNumberType.fromCode(code);
    }

    @Override
    public PhoneNumberService getService() {
        return service;
    }

    public void setService(PhoneNumberService service) {
        this.service = service;
    }
}
