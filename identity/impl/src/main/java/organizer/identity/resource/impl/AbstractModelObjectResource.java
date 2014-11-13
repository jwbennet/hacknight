package organizer.identity.resource.impl;

import organizer.Constants;
import organizer.PaginatedArrayList;
import organizer.PaginatedCollection;
import organizer.PaginatedList;
import organizer.dto.AbstractDataTransferObject;
import organizer.dto.Links;
import organizer.identity.resource.ModelObjectResource;
import organizer.mo.ModelObject;
import organizer.service.ModelObjectService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModelObjectResource<M extends ModelObject, D extends AbstractDataTransferObject<M>> implements ModelObjectResource<M, D> {

    public abstract ModelObjectService<M> getService();
    public abstract String getResourceName();

    @Context
    private Request request;
    @Context
    private HttpServletRequest httpRequest;
    @Context
    private UriInfo uriInfo;
    private Class<D> dtoClass;

    protected AbstractModelObjectResource(Class<D> dtoClass) {
        this.dtoClass = dtoClass;
    }

    @Override
    public Response getAll(int offset, int limit, Boolean active) {
        PaginatedCollection<M> modelObjects;
        if(active != null) {
            if(active) {
                modelObjects = getService().getActive(offset, limit);
            } else {
                modelObjects = getService().getInactive(offset, limit);
            }
        } else {
            modelObjects = getService().getAll(offset, limit);
        }
        return generateCollectionResponse(modelObjects);
    }

    @Override
    public Response getById(Long id) {
        return generateEntityResponse(getService().getById(id));
    }

    @Override
    public Response createRecord(D dto) {
        Response response = Response.serverError().build();
        M newMo = getService().create(dto.asModelObject());

        CacheControl cache = new CacheControl();
        cache.setMaxAge(Constants.CACHE_TTL);
        cache.setPrivate(true);

        EntityTag entityTag = new EntityTag(Integer.toString(newMo.hashCode()));

        List<Link> linkList = getEntityLinks(newMo.getId());
        Link[] links = new Link[linkList.size()];
        if(newMo instanceof AbstractDataTransferObject) {
            newMo = decorateEntity(getDtoClass().cast(newMo).asBuilder()).build();
        }
        return Response
                .created(getUriInfo().getBaseUriBuilder().path(getResourceName() + "/" + newMo.getId()).build())
                .entity(newMo)
                .cacheControl(cache)
                .lastModified(newMo.getLastUpdateDate().toDate())
                .tag(entityTag)
                .links(linkList.toArray(links))
                .build();
    }

    @Override
    public Response updateRecord(Long id, D dto) {
        if(dto.getId() != null && !id.equals(dto.getId())) {
            throw new IllegalArgumentException("The ID of the resource must match the ID provided in the request body");
        }
        M previous = getService().getById(id);
        EntityTag originalEntityTag = new EntityTag(Integer.toString(previous.hashCode()));

        Response.ResponseBuilder builder = getRequest().evaluatePreconditions(originalEntityTag);

        // client is not up to date (send back 412)
        if(builder != null) {
            return builder.build();
        }

        M updatedDto = getService().update(id, dto.asModelObject());

        CacheControl cache = new CacheControl();
        cache.setMaxAge(Constants.CACHE_TTL);
        cache.setPrivate(true);

        EntityTag newEntityTag = new EntityTag(Integer.toString(updatedDto.hashCode()));

        List<Link> linkList = getEntityLinks(id);
        Link[] links = new Link[linkList.size()];
        if(updatedDto instanceof AbstractDataTransferObject) {
            updatedDto = decorateEntity(getDtoClass().cast(updatedDto).asBuilder()).build();
        }
        return Response
                .ok(updatedDto)
                .cacheControl(cache)
                .lastModified(updatedDto.getLastUpdateDate().toDate())
                .tag(newEntityTag)
                .links(linkList.toArray(links))
                .build();
    }

    @Override
    public Response delete(Long id) {
        getService().delete(id);
        return Response.ok().build();
    }

    protected AbstractDataTransferObject.Builder<M> decorateEntity(AbstractDataTransferObject.Builder<M> modelObjectBuilder) {
        Links links = new Links().addLink("self", getUriInfo().getBaseUriBuilder().path(getResourceName()).path(modelObjectBuilder.getId().toString()).build());
        modelObjectBuilder.decorate(links);
        return modelObjectBuilder;
    }

    protected Response generateCollectionResponse(PaginatedCollection<M> modelObjects) {
        PaginatedList<M> decoratedCollection = new PaginatedArrayList<>();
        decoratedCollection.setOffset(modelObjects.getOffset()).setLimit(modelObjects.getLimit()).setCount(modelObjects.getCount());
        for(M modelObject : modelObjects ) {
            modelObject = decorateEntity(getDtoClass().cast(modelObject).asBuilder()).build();
            decoratedCollection.add(modelObject);
        }

        GenericEntity<PaginatedCollection<M>> entity = new GenericEntity<PaginatedCollection<M>>(decoratedCollection) {};
        List<Link> linkList = getCollectionLinks(modelObjects.getOffset(), modelObjects.getLimit(), modelObjects.getCount());
        Link[] links = new Link[linkList.size()];
        return Response.ok(entity).links(linkList.toArray(links)).build();
    }

    protected Response generateEntityResponse(M modelObject) {
        CacheControl cache = new CacheControl();
        cache.setMaxAge(Constants.CACHE_TTL);
        cache.setPrivate(true);

        EntityTag entityTag = new EntityTag(Integer.toString(modelObject.hashCode()));
        Response.ResponseBuilder responseBuilder = getRequest().evaluatePreconditions(modelObject.getLastUpdateDate().toDate(), entityTag);

        List<Link> linkList = getEntityLinks(modelObject.getId());
        Link[] links = new Link[linkList.size()];
        if(modelObject instanceof AbstractDataTransferObject) {
            modelObject = decorateEntity(getDtoClass().cast(modelObject).asBuilder()).build();
        }

        if(responseBuilder == null){
            responseBuilder = Response.ok(modelObject);
            responseBuilder.tag(entityTag);
        }

        return responseBuilder
                .cacheControl(cache)
                .lastModified(modelObject.getLastUpdateDate().toDate())
                .links(linkList.toArray(links))
                .build();
    }

    protected UriBuilder getCollectionUriBuilder() {
        return getUriInfo().getBaseUriBuilder().path(getResourceName());
    }

    protected Link generateCollectionLink(String rel, String title) {
        return generateCollectionLink("", rel, title);
    }

    protected Link generateCollectionLink(String subPath, String rel, String title) {
        UriBuilder collectionUriBuilder = getCollectionUriBuilder();
        if( StringUtils.isNotBlank(subPath)) {
            collectionUriBuilder.path(subPath);
        }
        return generateCollectionLink(collectionUriBuilder, rel, title);
    }

    protected Link generateCollectionLink(UriBuilder collectionUriBuilder, String rel, String title) {
        return Link.fromUriBuilder(collectionUriBuilder).rel(rel).title(title).build();
    }

    protected List<Link> getBaseLinks() {
        List<Link> linkList = new ArrayList<>();
        linkList.add(Link.fromUri(getUriInfo().getRequestUri()).rel("self").title("Current request URI").build());
        linkList.add(generateCollectionLink("all", "Collection of all " + getResourceName()));
        linkList.add(generateCollectionLink("create", "Create record in " + getResourceName()));
        return linkList;
    }

    protected List<Link> getCollectionLinks(int offset, int limit, long count) {
        List<Link> linkList = getBaseLinks();
        int previousPageOffset = Math.max(offset - limit, 0);
        int nextPageOffset = offset + limit;
        if(offset > 0) {
            linkList.add(generateCollectionLink(getCollectionUriBuilder().queryParam("offset", previousPageOffset).queryParam("limit", limit), "prev", "Previous page"));
        }
        if(nextPageOffset < count) {
            linkList.add(generateCollectionLink(getCollectionUriBuilder().queryParam("offset", nextPageOffset).queryParam("limit", limit), "next", "Next page"));
        }
        return linkList;
    }

    protected UriBuilder getEntityUriBuilder(Long id) {
        return getUriInfo().getBaseUriBuilder().path(getResourceName()).path(id.toString());
    }

    protected Link generateEntityLink(Long id, String rel, String title) {
        return generateEntityLink(id, "", rel, title);
    }

    protected Link generateEntityLink(Long id, String subPath, String rel, String title) {
        UriBuilder entityUriBuilder = getEntityUriBuilder(id);
        if( StringUtils.isNotBlank(subPath)) {
            entityUriBuilder.path(subPath);
        }
        return generateEntityLink(entityUriBuilder, rel, title);
    }

    protected Link generateEntityLink(UriBuilder entityUriBuilder, String rel, String title) {
        return Link.fromUriBuilder(entityUriBuilder).rel(rel).title(title).build();
    }

    protected List<Link> getEntityLinks(Long id) {
        List<Link> linkList = getBaseLinks();
        linkList.add(generateEntityLink(id, "update", "Update record " + id));
        linkList.add(generateEntityLink(id, "delete", "Delete record " + id));
        return linkList;
    }

    protected Request getRequest() {
        return request;
    }

    public HttpServletRequest getHttpRequest() {
        return httpRequest;
    }

    protected UriInfo getUriInfo() {
        return uriInfo;
    }

    public Class<D> getDtoClass() {
        return dtoClass;
    }
}
