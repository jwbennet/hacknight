package organizer.identity.resource;

import organizer.Constants;
import organizer.identity.dto.SkypeHandleDto;
import organizer.identity.mo.SkypeHandle;
import com.wordnik.swagger.annotations.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/" + SkypeHandleResource.RESOURCE_NAME)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Api(value = "/" + SkypeHandleResource.RESOURCE_NAME, description = "Operations on skype handles")
public interface SkypeHandleResource extends ContactInformationResource<SkypeHandle, SkypeHandleDto> {

    public static final String RESOURCE_NAME = "skypeHandles";

    @Override
    @GET
    @Path("/{id}")
    @ApiOperation(
            value = "Find skype handle by ID",
            notes = "Returns the skype handle matching the provided ID",
            response = SkypeHandleDto.class
    )
    Response getById(@ApiParam(value = "ID of the skype handle to fetch", required = true) @PathParam("id") Long id);

    @GET
    @Path("")
    @ApiOperation(
            value = "Find all skype handles",
            notes = "Returns a paginated list of all skype handles. If the active flag is passed then only active or inactive skype handles will be returned based on its value.",
            response = SkypeHandleDto.class,
            responseContainer = "Array"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "If contact type and contact ID are provided only the skype handles for that contact will be returned.", name = "contactType", dataType = "string", paramType = "query", allowableValues = "P,G"),
            @ApiImplicitParam(value = "If contact ID and contact type are provided only the skype handles for that contact will be returned.", name = "contactId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Filter skype handles by person ID. Shorthand for finding by contact type and ID.", name = "personId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Filter skype handles by group ID. Shorthand for finding by contact type and ID.", name = "groupId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Returns the primary skype handle if contact type and ID are provided.", name = "primary", dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(value = "Returns the active skype handle of the given type if contact type and ID are provided.", name = "type", dataType = "string", paramType = "query", allowableValues = "PRM,PRFR,OTH")
    })
    Response getAll(@ApiParam(value = "Number of rows to offset from start of collection") @DefaultValue("0") @QueryParam("offset") int offset,
                    @ApiParam(value = "Limit of the number of rows to return") @DefaultValue( Constants.DEFAULT_RESULTS_LIMIT_STRING) @QueryParam("limit") int limit,
                    @ApiParam(value = "Active/Inactive indicator. If not provided all skype handles will be returned.") @QueryParam("active") Boolean active);

    @POST
    @Path("")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(
            value = "Create skype handle",
            notes = "Creates a skype handle record",
            response = SkypeHandleDto.class
    )
    @Valid
    Response create(@NotNull(message = "Skype handle cannot be null") @Valid SkypeHandleDto dto);

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(
            value = "Update skype handle",
            notes = "Updates a skype handle record",
            response = SkypeHandleDto.class
    )
    @Valid
    Response update(@ApiParam(value = "ID of the skype handle to update", required = true) @PathParam( "id" ) Long id, @NotNull(message = "Skype handle cannot be null") @Valid SkypeHandleDto dto);

    @DELETE
    @Path("/{id}")
    @ApiOperation(
            value = "Delete skype handle",
            notes = "Deletes a skype handle record"
    )
    Response delete(@ApiParam(value = "ID of the skype handle to delete", required = true) @PathParam("id") Long id);

}
