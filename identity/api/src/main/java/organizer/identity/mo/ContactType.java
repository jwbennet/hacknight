package organizer.identity.mo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "contactType")
@XmlType(name = "ContactTypeType")
@XmlEnum
public enum ContactType {
    @XmlEnumValue(ContactType.PERSON_CODE) PERSON(ContactType.PERSON_CODE),
    @XmlEnumValue(ContactType.GROUP_CODE) GROUP(ContactType.GROUP_CODE);

    private static final String PERSON_CODE = "P";
    private static final String GROUP_CODE = "G";

    private final String code;

    private ContactType(String code) {
        this.code = code;
    }

    public static ContactType fromCode(String code) {
        switch ( code ) {
            case PERSON_CODE:
                return PERSON;
            case GROUP_CODE:
                return GROUP;
        }
        return null;
    }

    public String getCode() {
        return code;
    }
}
