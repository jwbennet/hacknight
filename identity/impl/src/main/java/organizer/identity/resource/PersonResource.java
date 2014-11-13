package organizer.identity.resource;

import organizer.identity.dto.*;
import organizer.identity.mo.Person;
import organizer.Constants;
import com.wordnik.swagger.annotations.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/" + PersonResource.RESOURCE_NAME)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Api(value = "/" + PersonResource.RESOURCE_NAME, description = "Operations on people")
public interface PersonResource extends ModelObjectResource<Person, PersonDto> {

    public static final String RESOURCE_NAME = "people";

    @Override
    @GET
    @Path("/{id}")
    @ApiOperation(
            value = "Find person by ID",
            notes = "Returns the person matching the provided ID",
            response = PersonDto.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Additional fields", name = "fields", dataType = "string", paramType = "query", allowableValues = "name,address,emailAddress,phoneNumber", allowMultiple = true)
    })
    Response getById(@ApiParam(value = "ID of the person to fetch", required = true) @PathParam("id") Long id);

    @GET
    @Path("")
    @ApiOperation(
            value = "Find all people",
            notes = "Returns a paginated list of all people. If the active flag is passed then only active or inactive people will be returned based on its value.",
            response = PersonDto.class,
            responseContainer = "Array"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Filter by username", name = "username", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "Additional fields", name = "fields", dataType = "string", paramType = "query", allowableValues = "name,address,emailAddress,phoneNumber", allowMultiple = true)
    })
    Response getAll(@ApiParam(value = "Number of rows to offset from start of collection.") @DefaultValue("0") @QueryParam("offset") int offset,
                    @ApiParam(value = "The number of rows to return.") @DefaultValue( Constants.DEFAULT_RESULTS_LIMIT_STRING) @QueryParam("limit") int limit,
                    @ApiParam(value = "Active/Inactive indicator. If not provided all people will be returned.") @QueryParam("active") Boolean active);

    @POST
    @Path("")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(
            value = "Create person",
            notes = "Creates a person record",
            response = PersonDto.class
    )
    @Valid
    Response create(@NotNull(message = "Person cannot be null") @Valid PersonDto dto);

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(
            value = "Update person",
            notes = "Updates a person record",
            response = PersonDto.class
    )
    @Valid
    Response update(@ApiParam(value = "ID of the person to update", required = true) @PathParam("id") Long id, @NotNull(message = "Person cannot be null") @Valid PersonDto dto);

    @DELETE
    @Path("/{id}")
    @ApiOperation(
            value = "Delete person",
            notes = "Deletes a person record"
    )
    Response delete(@ApiParam(value = "ID of the person to delete", required = true) @PathParam("id") Long id);

    @GET
    @Path("/{id}/" + NameResource.RESOURCE_NAME)
    @ApiOperation(
            value = "Get names for person",
            notes = "Fetches all name records for a given person",
            response = NameDto.class,
            responseContainer = "Array"
    )
    Response getNames(@ApiParam(value = "ID of the person to fetch", required = true) @PathParam("id") Long personId);

    @GET
    @Path("/{id}/" + AddressResource.RESOURCE_NAME)
    @ApiOperation(
            value = "Get addresses for person",
            notes = "Fetches all active addresses for a given person",
            response = AddressDto.class,
            responseContainer = "Array"
    )
    Response getAddresses(@ApiParam(value = "ID of the person to fetch", required = true) @PathParam("id") Long personId);

    @GET
    @Path("/{id}/" + EmailAddressResource.RESOURCE_NAME)
    @ApiOperation(
            value = "Get email addresses for person",
            notes = "Fetches all active email addresses for a given person",
            response = EmailAddressDto.class,
            responseContainer = "Array"
    )
    Response getEmailAddresses(@ApiParam(value = "ID of the person to fetch", required = true) @PathParam("id") Long personId);

    @GET
    @Path("/{id}/" + PhoneNumberResource.RESOURCE_NAME)
    @ApiOperation(
            value = "Get phone numbers for person",
            notes = "Fetches all active phone numbers for a given person",
            response = PhoneNumberDto.class,
            responseContainer = "Array"
    )
    Response getPhoneNumbers(@PathParam("id") Long personId);

    @GET
    @Path("/{id}/" + SkypeHandleResource.RESOURCE_NAME)
    @ApiOperation(
            value = "Get skype handles for person",
            notes = "Fetches all active Skype handles for a given person",
            response = SkypeHandleDto.class,
            responseContainer = "Array"
    )
    Response getSkypeHandles(@ApiParam(value = "ID of the person to fetch", required = true) @PathParam("id") Long personId);

    @GET
    @Path("/{id}/" + GroupResource.RESOURCE_NAME)
    @ApiOperation(
            value = "Get groups for person",
            notes = "Fetches all active groups which the given person is a member",
            response = GroupDto.class,
            responseContainer = "Array"
    )
    Response getGroups(@ApiParam(value = "ID of the person to fetch", required = true) @PathParam("id") Long personId);
}
