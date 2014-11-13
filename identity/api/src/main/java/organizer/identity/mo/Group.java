package organizer.identity.mo;

import organizer.identity.dto.GroupDto;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(GroupDto.Adapter.class)
public interface Group extends Contact {

    @NotBlank(message = "Alias cannot be blank")
    @Size(min = 6, max = 40, message = "Alias must be between 6 and 40 characters in length")
    String getAlias();
}
