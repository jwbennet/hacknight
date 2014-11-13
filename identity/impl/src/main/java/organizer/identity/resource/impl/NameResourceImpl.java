package organizer.identity.resource.impl;

import organizer.identity.dto.NameDto;
import organizer.identity.mo.Name;
import organizer.identity.mo.NameType;
import organizer.identity.resource.NameResource;
import organizer.identity.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

@Service(value = "nameResource")
public class NameResourceImpl extends AbstractContactInformationResource<Name, NameDto> implements NameResource {

    @Autowired
    private NameService service;

    public NameResourceImpl() {
        super(NameDto.class);
    }

    @Override
    public Response create(NameDto dto) {
        return super.createRecord(dto);
    }

    @Override
    public Response update(Long id, NameDto dto) {
        return super.updateRecord(id, dto);
    }

    @Override
    public String getResourceName() {
        return NameResource.RESOURCE_NAME;
    }

    @Override
    public NameType getContactInformationTypeByCode(String code) {
        return NameType.fromCode(code);
    }

    @Override
    public NameService getService() {
        return service;
    }

    public void setService(NameService service) {
        this.service = service;
    }
}
