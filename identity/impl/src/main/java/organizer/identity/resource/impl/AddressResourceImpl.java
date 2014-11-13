package organizer.identity.resource.impl;

import organizer.identity.mo.AddressType;
import organizer.identity.dto.AddressDto;
import organizer.identity.mo.Address;
import organizer.identity.resource.AddressResource;
import organizer.identity.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

@Service(value = "addressResource")
public class AddressResourceImpl extends AbstractContactInformationResource<Address, AddressDto> implements AddressResource {

    @Autowired
    private AddressService service;

    public AddressResourceImpl() {
        super(AddressDto.class);
    }

    @Override
    public Response create(AddressDto dto) {
        return super.createRecord(dto);
    }

    @Override
    public Response update(Long id, AddressDto dto) {
        return super.updateRecord(id, dto);
    }

    @Override
    public String getResourceName() {
        return AddressResource.RESOURCE_NAME;
    }

    @Override
    public AddressType getContactInformationTypeByCode(String code) {
        return AddressType.fromCode(code);
    }

    @Override
    public AddressService getService() {
        return service;
    }

    public void setService(AddressService service) {
        this.service = service;
    }
}
