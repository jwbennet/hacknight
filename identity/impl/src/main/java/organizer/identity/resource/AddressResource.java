package organizer.identity.resource;

import organizer.Constants;
import organizer.identity.dto.AddressDto;
import organizer.identity.mo.Address;
import com.wordnik.swagger.annotations.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/" + AddressResource.RESOURCE_NAME)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Api(value = "/" + AddressResource.RESOURCE_NAME, description = "Operations on addresses")
public interface AddressResource extends ContactInformationResource<Address, AddressDto> {

    public static final String RESOURCE_NAME = "addresses";

    @Override
    @GET
    @Path("/{id}")
    @ApiOperation(
            value = "Find address by ID",
            notes = "Returns the address matching the provided ID",
            response = AddressDto.class
    )
    Response getById(@ApiParam(value = "ID of the address to fetch", required = true) @PathParam("id") Long id);

    @GET
    @Path("")
    @ApiOperation(
            value = "Find all addresses",
            notes = "Returns a paginated list of all addresses. If the active flag is passed then only active or inactive addresses will be returned based on its value.",
            response = AddressDto.class,
            responseContainer = "Array"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "If contact type and contact ID are provided only the addresses for that contact will be returned.", name = "contactType", dataType = "string", paramType = "query", allowableValues = "P,G"),
            @ApiImplicitParam(value = "If contact ID and contact type are provided only the addresses for that contact will be returned.", name = "contactId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Filter addresses by person ID. Shorthand for finding by contact type and ID.", name = "personId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Filter addresses by group ID. Shorthand for finding by contact type and ID.", name = "groupId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Returns the primary address if contact type and ID are provided.", name = "primary", dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(value = "Returns the active address of the given type if contact type and ID are provided.", name = "type", dataType = "string", paramType = "query", allowableValues = "HOME,WORK,OTH")
    })
    Response getAll(@ApiParam(value = "Number of rows to offset from start of collection") @DefaultValue("0") @QueryParam("offset") int offset,
                    @ApiParam(value = "Limit of the number of rows to return") @DefaultValue( Constants.DEFAULT_RESULTS_LIMIT_STRING) @QueryParam("limit") int limit,
                    @ApiParam(value = "Active/Inactive indicator. If not provided all addresses will be returned.") @QueryParam("active") Boolean active);

    @POST
    @Path("")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(
            value = "Create address",
            notes = "Creates an address record",
            response = AddressDto.class
    )
    @Valid
    Response create(@NotNull(message = "Address cannot be null") @Valid AddressDto dto);

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(
            value = "Update address",
            notes = "Updates an address record",
            response = AddressDto.class
    )
    @Valid
    Response update(@ApiParam(value = "ID of the address to update", required = true) @PathParam( "id" ) Long id, @NotNull(message = "Address cannot be null") @Valid AddressDto dto);

    @DELETE
    @Path("/{id}")
    @ApiOperation(
            value = "Delete address",
            notes = "Deletes a address record"
    )
    Response delete(@ApiParam(value = "ID of the address to delete", required = true) @PathParam("id") Long id);

}
