package organizer.identity.mo;

import organizer.identity.dto.PhoneNumberDto;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(PhoneNumberDto.Adapter.class)
public interface PhoneNumber extends ContactInformation {

    @NotNull(message = "Phone number type cannot be null")
    PhoneNumberType getType();
    @NotBlank(message = "Phone number cannot be blank")
    @Size(max = 20, message = "Phone number cannot be more than 20 characters")
    @Pattern(regexp = "\\d{10,20}", message = "Phone number is an invalid format")
    String getPhoneNumber();
    @Size(max = 8, message = "Extension cannot be more than 8 characters")
    String getExtension();

}
