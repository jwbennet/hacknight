package organizer.identity.mo;

import organizer.identity.dto.NameDto;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(NameDto.Adapter.class)
public interface Name extends ContactInformation {

    @NotNull(message = "Name type cannot be null")
    NameType getType();
    @Size(max = 40, message = "Given name must be between 0 and 40 characters in length")
    String getGivenName();
    @Size(max = 40, message = "Middle name must be between 0 and 40 characters in length")
    String getMiddleName();
    @NotBlank(message = "Surname must not be blank")
    @Size(max = 80, message = "Given name must be between 0 and 80 characters in length")
    String getSurname();
    @Size(max = 10, message = "Suffix must be between 0 and 10 characters in length")
    String getSuffix();

    default String getDisplayName() {
        return Name.getDisplayName(this);
    }

    static String getDisplayName(Name name) {
        String formattedGivenName = "";
        String formattedMiddleName = "";
        if(StringUtils.isNotBlank(name.getGivenName())) {
            formattedGivenName = ", " + name.getGivenName();
            formattedMiddleName = StringUtils.isBlank(name.getMiddleName()) ? "" : " " + name.getMiddleName().substring(0, 1) + ".";
        }
        String formattedSurname = name.getSurname();
        String formattedSuffixName = StringUtils.isBlank(name.getSuffix()) ? "" : " " + name.getSuffix();
        return String.format("%s%s%s%s", formattedSurname, formattedSuffixName, formattedGivenName, formattedMiddleName);
    }
}
