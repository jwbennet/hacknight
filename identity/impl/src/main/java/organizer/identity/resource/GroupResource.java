package organizer.identity.resource;

import organizer.identity.dto.*;
import organizer.identity.mo.Group;
import organizer.Constants;
import com.wordnik.swagger.annotations.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/" + GroupResource.RESOURCE_NAME)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Api(value = "/" + GroupResource.RESOURCE_NAME, description = "Operations on groups")
public interface GroupResource extends ModelObjectResource<Group, GroupDto> {

    public static final String RESOURCE_NAME = "groups";

    @Override
    @GET
    @Path("/{id}")
    @ApiOperation(
            value = "Find group by ID",
            notes = "Returns the group matching the provided ID",
            response = GroupDto.class
    )
    Response getById(@ApiParam(value = "ID of the group to fetch", required = true) @PathParam("id") Long id);

    @GET
    @Path("")
    @ApiOperation(
            value = "Find all groups",
            notes = "Returns a paginated list of all groups. If the active flag is passed then only active or inactive groups will be returned based on its value.",
            response = GroupDto.class,
            responseContainer = "Array"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Filter by alias", name = "alias", dataType = "string", paramType = "query")
    })
    Response getAll(@ApiParam(value = "Number of rows to offset from start of collection") @DefaultValue("0") @QueryParam("offset") int offset,
                    @ApiParam(value = "Limit of the number of rows to return") @DefaultValue( Constants.DEFAULT_RESULTS_LIMIT_STRING) @QueryParam("limit") int limit,
                    @ApiParam(value = "Active/Inactive indicator. If not provided all groups will be returned.") @QueryParam("active") Boolean active);

    @POST
    @Path("")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(
            value = "Create group",
            notes = "Creates a group record",
            response = GroupDto.class
    )
    @Valid
    Response create(@NotNull(message = "Group cannot be null") @Valid GroupDto dto);

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(
            value = "Update group",
            notes = "Updates a group record",
            response = GroupDto.class
    )
    Response update(@ApiParam(value = "ID of the group to update", required = true) @PathParam( "id" ) Long id, @NotNull(message = "Group cannot be null") @Valid GroupDto dto);

    @DELETE
    @Path("/{id}")
    @ApiOperation(
            value = "Delete group",
            notes = "Deletes a group record"
    )
    Response delete(@ApiParam(value = "ID of the group to delete", required = true) @PathParam("id") Long id);

    @GET
    @Path("/{id}/" + NameResource.RESOURCE_NAME)
    @ApiOperation(
            value = "Get names for group",
            notes = "Fetches all name records for a given group",
            response = NameDto.class,
            responseContainer = "Array"
    )
    Response getNames(@ApiParam(value = "ID of the group", required = true)@PathParam( "id" ) Long groupId);

    @GET
    @Path("/{id}/" + AddressResource.RESOURCE_NAME)
    @ApiOperation(
            value = "Get addresses for group",
            notes = "Fetches all active addresses for a given group",
            response = AddressDto.class,
            responseContainer = "Array"
    )
    Response getAddresses(@ApiParam(value = "ID of the group", required = true)@PathParam( "id" ) Long groupId);

    @GET
    @Path("/{id}/" + EmailAddressResource.RESOURCE_NAME)
    @ApiOperation(
            value = "Get email addresses for group",
            notes = "Fetches all active email addresses for a given group",
            response = EmailAddressDto.class,
            responseContainer = "Array"
    )
    Response getEmailAddresses(@ApiParam(value = "ID of the group", required = true)@PathParam( "id" ) Long groupId);

    @GET
    @Path("/{id}/" + PhoneNumberResource.RESOURCE_NAME)
    @ApiOperation(
            value = "Get phone numbers for group",
            notes = "Fetches all active phone numbers for a given group",
            response = PhoneNumberDto.class,
            responseContainer = "Array"
    )
    Response getPhoneNumbers(@ApiParam(value = "ID of the group", required = true)@PathParam( "id" ) Long groupId);

    @GET
    @Path("/{id}/" + SkypeHandleResource.RESOURCE_NAME)
    @ApiOperation(
            value = "Get skype handles for group",
            notes = "Fetches all active Skype handles for a given group",
            response = SkypeHandleDto.class,
            responseContainer = "Array"
    )
    Response getSkypeHandles(@ApiParam(value = "ID of the group", required = true)@PathParam( "id" ) Long groupId);

    @GET
    @Path("/{id}/" + GroupResource.RESOURCE_NAME)
    @ApiOperation(
            value = "Get groups for group",
            notes = "Fetches all active groups which the given group is a member",
            response = GroupDto.class,
            responseContainer = "Array"
    )
    Response getGroups(@ApiParam(value = "ID of the group", required = true)@PathParam("id") Long groupId);
}
