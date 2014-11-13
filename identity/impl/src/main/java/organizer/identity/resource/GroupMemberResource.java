package organizer.identity.resource;

import organizer.Constants;
import organizer.identity.dto.GroupMemberDto;
import organizer.identity.mo.GroupMember;
import com.wordnik.swagger.annotations.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/" + GroupMemberResource.RESOURCE_NAME)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Api(value = "/" + GroupMemberResource.RESOURCE_NAME, description = "Operations on group memberships")
public interface GroupMemberResource extends ModelObjectResource<GroupMember, GroupMemberDto> {

    public static final String RESOURCE_NAME = "groupMembers";

    @Override
    @GET
    @Path("/{id}")
    @ApiOperation(
            value = "Find group membership by ID",
            notes = "Returns the group membership matching the provided ID",
            response = GroupMemberDto.class
    )
    Response getById(@ApiParam(value = "ID of the group membership to fetch", required = true) @PathParam("id") Long id);

    @GET
    @Path("")
    @ApiOperation(
            value = "Find all group memberships",
            notes = "Returns a paginated list of all group memberships. If the active flag is passed then only active or inactive group memberships will be returned based on its value.",
            response = GroupMemberDto.class,
            responseContainer = "Array"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "If contact type and contact ID are provided only the group memberships for that contact will be returned.", name = "contactType", dataType = "string", paramType = "query", allowableValues = "P,G"),
            @ApiImplicitParam(value = "If contact ID and contact type are provided only the group memberships for that contact will be returned.", name = "contactId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Filter group memberships by group ID.  If this parameter is provided only the memberships for the given group ID will be returned.", name = "groupId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Filter group memberships by member person ID. Shorthand for finding by contact type and ID.", name = "memberPersonId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Filter group memberships by member group ID. Shorthand for finding by contact type and ID.", name = "memberGroupId", dataType = "integer", paramType = "query")
    })
    Response getAll(@ApiParam(value = "Number of rows to offset from start of collection") @DefaultValue("0") @QueryParam("offset") int offset,
                    @ApiParam(value = "Limit of the number of rows to return") @DefaultValue( Constants.DEFAULT_RESULTS_LIMIT_STRING) @QueryParam("limit") int limit,
                    @ApiParam(value = "Active/Inactive indicator. If not provided all group memberships will be returned.") @QueryParam("active") Boolean active);

    @POST
    @Path("")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(
            value = "Create group membership",
            notes = "Creates a group membership record",
            response = GroupMemberDto.class
    )
    @Valid
    Response create(@NotNull(message = "Group member cannot be null") @Valid GroupMemberDto dto);

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(
            value = "Update group membership",
            notes = "Updates a group membership record",
            response = GroupMemberDto.class
    )
    @Valid
    Response update(@ApiParam(value = "ID of the group membership to update", required = true) @PathParam( "id" ) Long id, @NotNull(message = "Group member cannot be null") @Valid GroupMemberDto dto);

    @DELETE
    @Path("/{id}")
    @ApiOperation(
            value = "Delete group membership",
            notes = "Deletes a group membership record"
    )
    Response delete(@ApiParam(value = "ID of the group membership to delete", required = true) @PathParam("id") Long id);

}
