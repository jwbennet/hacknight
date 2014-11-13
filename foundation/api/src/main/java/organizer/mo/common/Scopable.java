package organizer.mo.common;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface Scopable {

    @NotNull(message = "Tenant ID must not be null")
    @Min(value = 1, message = "Tenant ID must be greater than zero")
    Long getTenantId();
}
