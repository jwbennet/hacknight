package organizer.identity.mo;

import organizer.identity.dto.SkypeHandleDto;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(SkypeHandleDto.Adapter.class)
public interface SkypeHandle extends ContactInformation {

    @NotNull(message = "Skype handle type cannot be null")
    SkypeHandleType getType();
    @NotBlank(message = "Username cannot be blank")
    @Size(max = 40, message = "Username cannot be more than 40 characters")
    String getUsername();
}
