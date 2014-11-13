package organizer.identity.mo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addressType")
@XmlType(name = "AddressTypeType")
@XmlEnum
public enum AddressType implements ContactInformationType {
    @XmlEnumValue(AddressType.HOME_CODE) HOME(AddressType.HOME_CODE),
    @XmlEnumValue(AddressType.WORK_CODE) WORK(AddressType.WORK_CODE),
    @XmlEnumValue(AddressType.OTHER_CODE) OTHER(AddressType.OTHER_CODE);

    private static final String HOME_CODE = "HOME";
    private static final String WORK_CODE = "WORK";
    private static final String OTHER_CODE = "OTH";

    private final String code;

    private AddressType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static AddressType fromCode(String code) {
        switch ( code ) {
            case HOME_CODE:
                return HOME;
            case WORK_CODE:
                return WORK;
            default:
                return OTHER;
        }
    }
}
