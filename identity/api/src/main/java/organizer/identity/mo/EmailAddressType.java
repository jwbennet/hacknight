package organizer.identity.mo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "emailAddressType")
@XmlType(name = "EmailAddressTypeType")
@XmlEnum
public enum EmailAddressType implements ContactInformationType {
    @XmlEnumValue(EmailAddressType.WORK_CODE) WORK(EmailAddressType.WORK_CODE),
    @XmlEnumValue(EmailAddressType.PERSONAL_CODE) PERSONAL(EmailAddressType.PERSONAL_CODE),
    @XmlEnumValue(EmailAddressType.OTHER_CODE) OTHER(EmailAddressType.OTHER_CODE);

    private static final String WORK_CODE = "WORK";
    private static final String PERSONAL_CODE = "PRSN";
    private static final String OTHER_CODE = "OTH";

    private final String code;

    private EmailAddressType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static EmailAddressType fromCode(String code) {
        switch ( code ) {
            case WORK_CODE:
                return WORK;
            case PERSONAL_CODE:
                return PERSONAL;
            default:
                return OTHER;
        }
    }
}
