package organizer.identity.resource;

import organizer.identity.dto.PhoneNumberDto;
import organizer.Constants;
import organizer.identity.mo.PhoneNumber;
import com.wordnik.swagger.annotations.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/" + PhoneNumberResource.RESOURCE_NAME)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Api(value = "/" + PhoneNumberResource.RESOURCE_NAME, description = "Operations on phone numbers")
public interface PhoneNumberResource extends ContactInformationResource<PhoneNumber, PhoneNumberDto> {

    public static final String RESOURCE_NAME = "phoneNumbers";

    @Override
    @GET
    @Path("/{id}")
    @ApiOperation(
            value = "Find phone number by ID",
            notes = "Returns the phone number matching the provided ID",
            response = PhoneNumberDto.class
    )
    Response getById(@ApiParam(value = "ID of the phone number to fetch", required = true) @PathParam("id") Long id);

    @GET
    @Path("")
    @ApiOperation(
            value = "Find all phone numbers",
            notes = "Returns a paginated list of all phone numbers. If the active flag is passed then only active or inactive phone numbers will be returned based on its value.",
            response = PhoneNumberDto.class,
            responseContainer = "Array"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "If contact type and contact ID are provided only the phone numbers for that contact will be returned.", name = "contactType", dataType = "string", paramType = "query", allowableValues = "P,G"),
            @ApiImplicitParam(value = "If contact ID and contact type are provided only the phone numbers for that contact will be returned.", name = "contactId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Filter phone numbers by person ID. Shorthand for finding by contact type and ID.", name = "personId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Filter phone numbers by group ID. Shorthand for finding by contact type and ID.", name = "groupId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Returns the primary phone number if contact type and ID are provided.", name = "primary", dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(value = "Returns the active phone number of the given type if contact type and ID are provided.", name = "type", dataType = "string", paramType = "query", allowableValues = "PRM,PRFR,OTH")
    })
    Response getAll(@ApiParam(value = "Number of rows to offset from start of collection") @DefaultValue("0") @QueryParam("offset") int offset,
                    @ApiParam(value = "Limit of the number of rows to return") @DefaultValue( Constants.DEFAULT_RESULTS_LIMIT_STRING) @QueryParam("limit") int limit,
                    @ApiParam(value = "Active/Inactive indicator. If not provided all phone numbers will be returned.") @QueryParam("active") Boolean active);

    @POST
    @Path("")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(
            value = "Create phone number",
            notes = "Creates a phone number record",
            response = PhoneNumberDto.class
    )
    @Valid
    Response create(@NotNull(message = "Phone number cannot be null") @Valid PhoneNumberDto dto);

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(
            value = "Update phone number",
            notes = "Updates a phone number record",
            response = PhoneNumberDto.class
    )
    @Valid
    Response update(@ApiParam(value = "ID of the phone number to update", required = true) @PathParam( "id" ) Long id, @NotNull(message = "Phone number cannot be null") @Valid PhoneNumberDto dto);

    @DELETE
    @Path("/{id}")
    @ApiOperation(
            value = "Delete phone number",
            notes = "Deletes a phone number record"
    )
    Response delete(@ApiParam(value = "ID of the phone number to delete", required = true) @PathParam("id") Long id);

}
