package organizer.identity.resource.impl;

import organizer.identity.dto.SkypeHandleDto;
import organizer.identity.mo.SkypeHandle;
import organizer.identity.mo.SkypeHandleType;
import organizer.identity.resource.SkypeHandleResource;
import organizer.identity.service.SkypeHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

@Service(value = "skypeHandleResource")
public class SkypeHandleResourceImpl extends AbstractContactInformationResource<SkypeHandle, SkypeHandleDto> implements SkypeHandleResource {

    @Autowired
    private SkypeHandleService service;

    public SkypeHandleResourceImpl() {
        super(SkypeHandleDto.class);
    }

    @Override
    public Response create(SkypeHandleDto dto) {
        return super.createRecord(dto);
    }

    @Override
    public Response update(Long id, SkypeHandleDto dto) {
        return super.updateRecord(id, dto);
    }

    @Override
    public String getResourceName() {
        return SkypeHandleResource.RESOURCE_NAME;
    }

    @Override
    public SkypeHandleType getContactInformationTypeByCode(String code) {
        return SkypeHandleType.fromCode(code);
    }

    @Override
    public SkypeHandleService getService() {
        return service;
    }

    public void setService(SkypeHandleService service) {
        this.service = service;
    }
}
