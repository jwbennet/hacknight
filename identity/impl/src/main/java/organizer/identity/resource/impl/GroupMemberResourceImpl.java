package organizer.identity.resource.impl;

import organizer.identity.dto.GroupMemberDto;
import organizer.identity.mo.ContactType;
import organizer.identity.mo.GroupMember;
import organizer.identity.resource.GroupMemberResource;
import organizer.identity.service.GroupMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@Service(value = "groupMemberResource")
public class GroupMemberResourceImpl extends AbstractModelObjectResource<GroupMember, GroupMemberDto> implements GroupMemberResource {

    @Autowired
    private GroupMemberService service;

    public GroupMemberResourceImpl() {
        super(GroupMemberDto.class);
    }

    @Override
    public Response getAll(int offset, int limit, Boolean active) {
        ContactType contactType = null;
        Set<Long> contactIds = new HashSet<>();
        String contactTypeCode = getHttpRequest().getParameter("contactType");
        String[] contactIdValues = getHttpRequest().getParameterValues("contactId");
        if(StringUtils.isNotBlank(contactTypeCode) && contactIdValues.length > 0) {
            contactType = ContactType.fromCode(contactTypeCode);
            for(String contactId : contactIdValues) {
                contactIds.add(Long.parseLong(contactId));
            }
        }
        String groupId = getHttpRequest().getParameter("groupId");
        String memberPersonId = getHttpRequest().getParameter("memberPersonId");
        String memberGroupId = getHttpRequest().getParameter("memberGroupId");
        if(contactType == null) {
            if(StringUtils.isNumeric(memberPersonId)) {
                contactType = ContactType.PERSON;
                contactIds.add(Long.parseLong(memberPersonId));
            } else if(StringUtils.isNumeric(memberGroupId)) {
                contactType = ContactType.GROUP;
                contactIds.add(Long.parseLong(memberGroupId));
            }
        }
        if(StringUtils.isNumeric(groupId)) {
            if(active != null && !active) {
                throw new IllegalArgumentException("Active parameter provided with false value but finding members by group ID always returns active results");
            }
            return generateCollectionResponse(getService().getMembersByGroupId(Long.parseLong(groupId), offset, limit));
        } else if(StringUtils.isNumeric(memberPersonId) || StringUtils.isNumeric(memberGroupId)) {
            URI endpoint = getUriInfo().getRequestUriBuilder().replaceQueryParam("memberPersonId").replaceQueryParam("memberGroupId").queryParam("contactType", contactType.getCode()).queryParam("contactId", contactIds.toArray()).build();
            return Response.temporaryRedirect(endpoint).build();
        } else if(contactType != null) {
            if(active != null && !active) {
                throw new IllegalArgumentException("Active parameter provided with false value but finding members by contact ID always returns active results");
            }
            return generateCollectionResponse(getService().getMembersByContacts(contactType, contactIds, offset, limit));
        }
        return super.getAll(offset, limit, active);
    }

    @Override
    public Response create(GroupMemberDto dto) {
        return super.createRecord(dto);
    }

    @Override
    public Response update(Long id, GroupMemberDto dto) {
        return super.updateRecord(id, dto);
    }

    @Override
    public String getResourceName() {
        return RESOURCE_NAME;
    }

    @Override
    public GroupMemberService getService() {
        return service;
    }

    public void setService(GroupMemberService service) {
        this.service = service;
    }
}
