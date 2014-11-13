package organizer.identity.mo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "phoneType")
@XmlType(name = "PhoneTypeType")
@XmlEnum
public enum PhoneNumberType implements ContactInformationType {
    @XmlEnumValue(PhoneNumberType.HOME_CODE) HOME(PhoneNumberType.HOME_CODE),
    @XmlEnumValue(PhoneNumberType.WORK_CODE) WORK(PhoneNumberType.WORK_CODE),
    @XmlEnumValue(PhoneNumberType.MOBILE_CODE) MOBILE(PhoneNumberType.MOBILE_CODE),
    @XmlEnumValue(PhoneNumberType.FAX_CODE) FAX(PhoneNumberType.FAX_CODE),
    @XmlEnumValue(PhoneNumberType.OTHER_CODE) OTHER(PhoneNumberType.OTHER_CODE);

    private static final String HOME_CODE = "HOME";
    private static final String WORK_CODE = "WORK";
    private static final String MOBILE_CODE = "MOBI";
    private static final String FAX_CODE = "FAX";
    private static final String OTHER_CODE = "OTH";

    private final String code;

    private PhoneNumberType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static PhoneNumberType fromCode(String code) {
        switch ( code ) {
            case HOME_CODE:
                return HOME;
            case WORK_CODE:
                return WORK;
            case MOBILE_CODE:
                return MOBILE;
            case FAX_CODE:
                return FAX;
            default:
                return OTHER;
        }
    }
}
