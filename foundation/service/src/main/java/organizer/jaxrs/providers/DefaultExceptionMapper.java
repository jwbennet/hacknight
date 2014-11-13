package organizer.jaxrs.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultExceptionMapper.class);

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(Exception exception) {
        ErrorDetail error = new ErrorDetail();
        if(exception.getMessage() != null) {
            error.addMessage(exception.getMessage());
        }

        Response response = null;
        if(exception instanceof NoResultException ) {
            error.setCode(ErrorCode.RESULT_NOT_FOUND).setDetail("This error is returned when a single result is expected to be found in the database but no result is returned.");
            response = Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
        if(exception instanceof ClientErrorException ) {
            ClientErrorException clientErrorException = (ClientErrorException)exception;
            switch(clientErrorException.getResponse().getStatus()) {
                case 404:
                    error.setCode(ErrorCode.HANDLER_NOT_FOUND).setDetail("No handlers could be found which match the request made");
                    break;
                case 406:
                    error.setCode(ErrorCode.NOT_ACCEPTABLE).setDetail("This error is returned when an Accept header is provided which requests a type which cannot be returned.");
                    break;
            }
            response = Response.fromResponse(clientErrorException.getResponse()).entity(error).build();
        }
        if(exception instanceof IllegalArgumentException) {
            error.setCode(ErrorCode.ILLEGAL_ARGUMENTS).setDetail("This error is returned when invalid arguments are provided to a method call");
            response = Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
        if(exception instanceof ConstraintViolationException) {
            error.clearMessages();
            ConstraintViolationException constraintViolationException = (ConstraintViolationException)exception;
            for(ConstraintViolation<?> violation : constraintViolationException.getConstraintViolations()) {
                error.addMessage(violation.getMessage());
            }
            error.setCode(ErrorCode.VALIDATION_FAILURE).setDetail("There was a validation failure due to invalid values passed in or being returned.  Check the provided messages for more detail.");
            response = Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
        if(error.getCode() == null) {
            LOG.error("Caught unhandled exception processing request", exception);
            error.setCode(ErrorCode.UNKNOWN).setDetail("This error is returned when a more specific error is not handled.  This usually means something went wrong on the server and the logs should be examined.");
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
        error.setMoreInfo(getUriInfo().getBaseUriBuilder().path("errors/" + error.getCode().getCode()).build());

        return response;
    }

    protected UriInfo getUriInfo() {
        return uriInfo;
    }
}
