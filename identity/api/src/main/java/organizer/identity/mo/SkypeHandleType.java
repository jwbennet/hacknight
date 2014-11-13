package organizer.identity.mo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "skypeHandleType")
@XmlType(name = "SkypeHandleTypeType")
@XmlEnum
public enum SkypeHandleType implements ContactInformationType {
    @XmlEnumValue(SkypeHandleType.WORK_CODE) WORK(SkypeHandleType.WORK_CODE),
    @XmlEnumValue(SkypeHandleType.PERSONAL_CODE) PERSONAL(SkypeHandleType.PERSONAL_CODE),
    @XmlEnumValue(SkypeHandleType.OTHER_CODE) OTHER(SkypeHandleType.OTHER_CODE);

    private static final String WORK_CODE = "WORK";
    private static final String PERSONAL_CODE = "PRSN";
    private static final String OTHER_CODE = "OTH";

    private final String code;

    private SkypeHandleType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static SkypeHandleType fromCode(String code) {
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
