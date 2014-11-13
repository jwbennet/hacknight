package organizer.identity.mo;

import organizer.identity.dto.EmailAddressDto;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter( EmailAddressDto.Adapter.class )
public interface EmailAddress extends ContactInformation {

    @NotNull(message = "Email address type cannot be null")
    EmailAddressType getType();
    @NotBlank(message = "Email address cannot be blank")
    @Email(message = "Email address is not formatted properly")
    @Size(max = 100, message = "Email address cannot be more than 100 characters")
    String getEmailAddress();

}
