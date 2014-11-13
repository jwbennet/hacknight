package organizer.identity.mo;

import organizer.identity.dto.GroupMemberDto;
import organizer.mo.ModelObject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(GroupMemberDto.Adapter.class)
public interface GroupMember extends ModelObject{

    @NotNull(message = "Group ID cannot be null")
    @Min(value = 1, message = "Group ID must be greater than zero")
    Long getGroupId();
    @NotNull(message = "Contact type must not be null")
    ContactType getContactType();
    @NotNull(message = "Contact ID must not be null")
    @Min(value = 1, message = "Contact ID must be greater than 1")
    Long getContactId();

}
