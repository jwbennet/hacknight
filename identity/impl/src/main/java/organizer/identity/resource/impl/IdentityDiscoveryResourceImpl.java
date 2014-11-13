package organizer.identity.resource.impl;

import organizer.identity.resource.*;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

@Service(value = "identityDiscoveryResource")
public final class IdentityDiscoveryResourceImpl implements IdentityDiscoveryResource {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response discover() {
        List<Link> linkList = getResourceLinks();
        Link[] links = new Link[linkList.size()];
        return Response.ok("Welcome to the Organizer Identity API.  See the Link headers for places to get started.").links(linkList.toArray(links)).build();
    }

    @Override
    public Response discoverHtml() {
        return Response.temporaryRedirect(getUriInfo().getBaseUriBuilder().replacePath("identity/api-docs").build()).build();
    }

    private List<Link> getResourceLinks() {
        List<Link> resourceLinks = new ArrayList<>();
        resourceLinks.add(generateLink(getUriInfo().getBaseUriBuilder().replacePath("identity/api-docs"), "docs", "API Documentation"));
        resourceLinks.add(generateLink(getUriInfo().getBaseUriBuilder().path(AddressResource.RESOURCE_NAME), AddressResource.RESOURCE_NAME, "Address API"));
        resourceLinks.add(generateLink(getUriInfo().getBaseUriBuilder().path(EmailAddressResource.RESOURCE_NAME), EmailAddressResource.RESOURCE_NAME, "Email Address API"));
        resourceLinks.add(generateLink(getUriInfo().getBaseUriBuilder().path(GroupResource.RESOURCE_NAME), GroupResource.RESOURCE_NAME, "Group API"));
        resourceLinks.add(generateLink(getUriInfo().getBaseUriBuilder().path(GroupMemberResource.RESOURCE_NAME), GroupMemberResource.RESOURCE_NAME, "Group Member API"));
        resourceLinks.add(generateLink(getUriInfo().getBaseUriBuilder().path(NameResource.RESOURCE_NAME), NameResource.RESOURCE_NAME, "Name API"));
        resourceLinks.add(generateLink(getUriInfo().getBaseUriBuilder().path(PersonResource.RESOURCE_NAME), PersonResource.RESOURCE_NAME, "Person API"));
        resourceLinks.add(generateLink(getUriInfo().getBaseUriBuilder().path(PhoneNumberResource.RESOURCE_NAME), PhoneNumberResource.RESOURCE_NAME, "Phone Number API"));
        resourceLinks.add(generateLink(getUriInfo().getBaseUriBuilder().path(SkypeHandleResource.RESOURCE_NAME), SkypeHandleResource.RESOURCE_NAME, "Skype Handle API"));
        return resourceLinks;
    }

    private Link generateLink(UriBuilder entityUriBuilder, String rel, String title) {
        return Link.fromUriBuilder(entityUriBuilder).rel(rel).title(title).build();
    }

    public UriInfo getUriInfo() {
        return uriInfo;
    }
}
