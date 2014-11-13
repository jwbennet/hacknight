package organizer.identity.resource;

import organizer.identity.mo.ContactInformation;
import organizer.identity.dto.ContactInformationDto;

public interface ContactInformationResource<M extends ContactInformation, D extends ContactInformationDto<M>> extends ModelObjectResource<M, D> {
}
