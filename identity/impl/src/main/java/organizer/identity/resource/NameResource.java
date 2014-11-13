package organizer.identity.resource;

import organizer.Constants;
import organizer.identity.dto.NameDto;
import organizer.identity.mo.Name;
import com.wordnik.swagger.annotations.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/" + NameResource.RESOURCE_NAME)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Api(value = "/" + NameResource.RESOURCE_NAME, description = "Operations on names")
public interface NameResource extends ContactInformationResource<Name, NameDto> {

    public static final String RESOURCE_NAME = "names";

    @Override
    @GET
    @Path("/{id}")
    @ApiOperation(
            value = "Find name by ID",
            notes = "Returns the name matching the provided ID",
            response = NameDto.class
    )
    Response getById(@ApiParam(value = "ID of the name to fetch", required = true) @PathParam("id") Long id);

    @GET
    @Path("")
    @ApiOperation(
            value = "Find all names",
            notes = "Returns a paginated list of all names. If the active flag is passed then only active or inactive names will be returned based on its value.",
            response = NameDto.class,
            responseContainer = "Array"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "If contact type and contact ID are provided only the names for that contact will be returned.", name = "contactType", dataType = "string", paramType = "query", allowableValues = "P,G"),
            @ApiImplicitParam(value = "If contact ID and contact type are provided only the names for that contact will be returned.", name = "contactId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Filter names by person ID. Shorthand for finding by contact type and ID.", name = "personId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Filter names by group ID. Shorthand for finding by contact type and ID.", name = "groupId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Returns the primary name if contact type and ID are provided.", name = "primary", dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(value = "Returns the active name of the given type if contact type and ID are provided.", name = "type", dataType = "string", paramType = "query", allowableValues = "PRM,PRFR,OTH")
    })
    Response getAll(@ApiParam(value = "Number of rows to offset from start of collection") @DefaultValue("0") @QueryParam("offset") int offset,
                    @ApiParam(value = "Limit of the number of rows to return") @DefaultValue( Constants.DEFAULT_RESULTS_LIMIT_STRING) @QueryParam("limit") int limit,
                    @ApiParam(value = "Active/Inactive indicator. If not provided all names will be returned.") @QueryParam("active") Boolean active);

    @POST
    @Path("")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(
            value = "Create name",
            notes = "Creates a name record",
            response = NameDto.class
    )
    @Valid
    Response create(@NotNull(message = "Name cannot be null") @Valid NameDto dto);

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(
            value = "Update name",
            notes = "Updates a name record",
            response = NameDto.class
    )
    @Valid
    Response update(@ApiParam(value = "ID of the name to update", required = true) @PathParam( "id" ) Long id, @NotNull(message = "Name cannot be null") @Valid NameDto dto);

    @DELETE
    @Path("/{id}")
    @ApiOperation(
            value = "Delete name",
            notes = "Deletes a name record"
    )
    Response delete(@ApiParam(value = "ID of the name to delete", required = true) @PathParam("id") Long id);
}
