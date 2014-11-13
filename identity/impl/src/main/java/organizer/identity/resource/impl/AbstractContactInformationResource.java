package organizer.identity.resource.impl;

import organizer.PaginatedCollection;
import organizer.identity.dto.ContactInformationDto;
import organizer.identity.mo.ContactInformation;
import organizer.identity.mo.ContactInformationType;
import organizer.identity.mo.ContactType;
import organizer.identity.resource.ContactInformationResource;
import organizer.identity.service.ContactInformationService;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.Response;
import java.net.URI;

public abstract class AbstractContactInformationResource<M extends ContactInformation, D extends ContactInformationDto<M>> extends AbstractModelObjectResource<M, D> implements ContactInformationResource<M, D> {

    public abstract ContactInformationService<M> getService();
    public abstract ContactInformationType getContactInformationTypeByCode(String code);

    protected AbstractContactInformationResource(Class<D> dtoClass) {
        super(dtoClass);
    }

    @Override
    public Response getAll(int offset, int limit, Boolean active) {
        ContactType contactType = null;
        Long contactId = null;
        String contactTypeCode = getHttpRequest().getParameter("contactType");
        String contactIdString = getHttpRequest().getParameter("contactId");
        if(StringUtils.isNotBlank(contactTypeCode) && StringUtils.isNumeric(contactIdString)) {
            contactType = ContactType.fromCode(contactTypeCode);
            contactId = Long.parseLong(contactIdString);
        }
        String personId = getHttpRequest().getParameter("personId");
        String groupId = getHttpRequest().getParameter("groupId");
        if(contactType == null) {
            if(StringUtils.isNumeric(personId)) {
                contactType = ContactType.PERSON;
                contactId = Long.parseLong(personId);
            } else if(StringUtils.isNumeric(groupId)) {
                contactType = ContactType.GROUP;
                contactId = Long.parseLong(groupId);
            }
        }
        String primary = getHttpRequest().getParameter("primary");
        String typeCode = getHttpRequest().getParameter("type");
        if( StringUtils.isNotBlank(primary)) {
            if(active != null && !active) {
                throw new IllegalArgumentException("Active parameter provided with false value but primary results must be active");
            }
            ContactInformation contactInformation = getService().getPrimaryByContact(contactType, contactId);
            URI resourceEndpoint = getUriInfo().getBaseUriBuilder().path(getResourceName() + "/" + contactInformation.getId()).build();
            return Response.temporaryRedirect(resourceEndpoint).build();
        } else if(StringUtils.isNotBlank(typeCode)) {
            if(active != null && !active) {
                throw new IllegalArgumentException("Active parameter provided with false value but retrieving by type only returns active results");
            }
            ContactInformation contactInformation = getService().getByType(contactType, contactId, getContactInformationTypeByCode(typeCode));
            URI resourceEndpoint = getUriInfo().getBaseUriBuilder().path(getResourceName() + "/" + contactInformation.getId()).build();
            return Response.temporaryRedirect(resourceEndpoint).build();
        } else if(StringUtils.isNumeric(personId) || StringUtils.isNumeric(groupId)) {
            URI endpoint = getUriInfo().getRequestUriBuilder().replaceQueryParam("personId").replaceQueryParam("groupId").queryParam("contactType", contactType.getCode()).queryParam("contactId", contactId).build();
            return Response.temporaryRedirect(endpoint).build();
        } else if(contactType != null) {
            PaginatedCollection<M> modelObjects;
            if(active != null) {
                if(active) {
                    modelObjects = getService().getActiveByContact(contactType, contactId, offset, limit);
                } else {
                    modelObjects = getService().getInactiveByContact(contactType, contactId, offset, limit);
                }
            } else {
                modelObjects = getService().getByContact(contactType, contactId, offset, limit);
            }
            return generateCollectionResponse(modelObjects);
        }

        return super.getAll(offset, limit, active);
    }
}
