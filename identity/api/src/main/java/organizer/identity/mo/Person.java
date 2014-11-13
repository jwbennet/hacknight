package organizer.identity.mo;

import organizer.identity.dto.PersonDto;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(PersonDto.Adapter.class)
public interface Person extends Contact {

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 6, max = 40, message = "Username must be between 6 and 40 characters in length")
    String getUsername();
    @Past(message = "Birthday must be in the past")
    DateTime getBirthday();

}
