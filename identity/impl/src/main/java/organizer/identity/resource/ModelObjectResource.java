package organizer.identity.resource;

import organizer.dto.AbstractDataTransferObject;
import organizer.mo.ModelObject;
import organizer.Constants;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public interface ModelObjectResource<M extends ModelObject, D extends AbstractDataTransferObject<M>> {

    @GET
    @Path("")
    Response getAll(@DefaultValue("0") @QueryParam("offset") @NotNull @Min(value = 0, message = "Offset must be greater than or equal to zero")  int offset,
                    @DefaultValue(Constants.DEFAULT_RESULTS_LIMIT_STRING) @QueryParam("limit") @NotNull @Min(value = 1, message = "Limit must be greater than zero")  int limit,
                    @QueryParam("active") Boolean active);

    @GET
    @Path("/{id}")
    @Valid
    Response getById(@PathParam("id") @NotNull @Min(value = 1, message = "ID must be greater than zero") Long id);

    @Valid
    Response createRecord(@Valid D dto);

    @Valid
    Response updateRecord(@PathParam("id") @NotNull @Min(value = 1, message = "ID must be greater than zero") Long id, @Valid D dto);

    @DELETE
    @Path("/{id}")
    @Valid
    Response delete(@PathParam("id") @NotNull @Min(value = 1, message = "ID must be greater than zero") Long id);
}
