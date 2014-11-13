package organizer.identity.mo;

import organizer.identity.dto.AddressDto;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(AddressDto.Adapter.class)
public interface Address extends ContactInformation {

    @NotNull(message = "Address type cannot be null")
    AddressType getType();
    @NotBlank(message = "Address cannot be blank")
    @Size(max = 45, message = "Address line 1 cannot be more than 45 characters")
    String getAddressLine1();
    @Size(max = 45, message = "Address line 2 cannot be more than 45 characters")
    String getAddressLine2();
    @Size(max = 45, message = "Address line 3 cannot be more than 45 characters")
    String getAddressLine3();
    @NotBlank(message = "City cannot be blank")
    @Size(max = 30, message = "City cannot be more than 30 characters")
    String getCity();
    @NotBlank(message = "State cannot be blank")
    @Size(max = 2, message = "State cannot be more than 2 characters")
    String getState();
    @NotBlank(message = "Postal code cannot be blank")
    @Pattern(regexp = "^\\d{5}(?:[-\\s]\\d{4})?$", message = "Postal code is an invalid format")
    @Size(max = 15, message = "Postal code cannot be more than 15 characters")
    String getPostalCode();
    @NotBlank(message = "Country cannot be blank")
    @Size(max = 2, message = "Country cannot be more than 2 characters")
    String getCountry();

}
