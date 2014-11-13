package organizer.identity.mo;

import organizer.mo.ModelObject;
import organizer.mo.common.Defaultable;
import organizer.mo.common.Typed;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface ContactInformation extends ModelObject, Defaultable, Typed {

    @NotNull(message = "Contact type must not be null")
    ContactType getContactType();
    @NotNull(message = "Contact ID must not be null")
    @Min(value = 1, message = "Contact ID must be greater than 1")
    Long getContactId();
    ContactInformationType getType();

}
