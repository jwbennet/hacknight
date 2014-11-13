package organizer.identity.resource;

import organizer.identity.dto.EmailAddressDto;
import organizer.identity.mo.EmailAddress;
import organizer.Constants;
import com.wordnik.swagger.annotations.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/" + EmailAddressResource.RESOURCE_NAME)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Api(value = "/" + EmailAddressResource.RESOURCE_NAME, description = "Operations on email addresses")
public interface EmailAddressResource extends ContactInformationResource<EmailAddress, EmailAddressDto> {

    public static final String RESOURCE_NAME = "emailAddresses";

    @Override
    @GET
    @Path("/{id}")
    @ApiOperation(
            value = "Find email address by ID",
            notes = "Returns the email address matching the provided ID",
            response = EmailAddressDto.class
    )
    Response getById(@ApiParam(value = "ID of the email address to fetch", required = true) @PathParam("id") Long id);

    @GET
    @Path("")
    @ApiOperation(
            value = "Find all email addresses",
            notes = "Returns a paginated list of all email addresses. If the active flag is passed then only active or inactive email address will be returned based on its value.",
            response = EmailAddressDto.class,
            responseContainer = "Array"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "If contact type and contact ID are provided only the email addresses for that contact will be returned.", name = "contactType", dataType = "string", paramType = "query", allowableValues = "P,G"),
            @ApiImplicitParam(value = "If contact ID and contact type are provided only the email addresses for that contact will be returned.", name = "contactId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Filter email addresses by person ID. Shorthand for finding by contact type and ID.", name = "personId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Filter email addresses by group ID. Shorthand for finding by contact type and ID.", name = "groupId", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(value = "Returns the primary email address if contact type and ID are provided.", name = "primary", dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(value = "Returns the active email address of the given type if contact type and ID are provided.", name = "type", dataType = "string", paramType = "query", allowableValues = "WORK,PRSN,OTH")
    })
    Response getAll(@ApiParam(value = "Number of rows to offset from start of collection") @DefaultValue("0") @QueryParam("offset") int offset,
                    @ApiParam(value = "Limit of the number of rows to return") @DefaultValue( Constants.DEFAULT_RESULTS_LIMIT_STRING) @QueryParam("limit") int limit,
                    @ApiParam(value = "Active/Inactive indicator. If not provided all email addresses will be returned.") @QueryParam("active") Boolean active);

    @POST
    @Path("")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(
            value = "Create email address",
            notes = "Creates an email address record",
            response = EmailAddressDto.class
    )
    @Valid
    Response create(@NotNull(message = "Email address cannot be null") @Valid EmailAddressDto dto);

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(
            value = "Update email address",
            notes = "Updates an email address record",
            response = EmailAddressDto.class
    )
    @Valid
    Response update(@ApiParam(value = "ID of the email address to update", required = true) @PathParam( "id" ) Long id, @NotNull(message = "Email address cannot be null") @Valid EmailAddressDto dto);

    @DELETE
    @Path("/{id}")
    @ApiOperation(
            value = "Delete email address",
            notes = "Deletes an email address record"
    )
    Response delete(@ApiParam(value = "ID of the email address to delete", required = true) @PathParam("id") Long id);

}
