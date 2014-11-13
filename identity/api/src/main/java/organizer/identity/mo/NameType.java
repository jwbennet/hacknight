package organizer.identity.mo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "nameType")
@XmlType(name = "NameTypeType")
@XmlEnum
public enum NameType implements ContactInformationType {
    @XmlEnumValue(NameType.PRIMARY_CODE) PRIMARY(NameType.PRIMARY_CODE),
    @XmlEnumValue(NameType.PREFERRED_CODE) PREFERRED(NameType.PREFERRED_CODE),
    @XmlEnumValue(NameType.OTHER_CODE) OTHER(NameType.OTHER_CODE);

    private static final String PRIMARY_CODE = "PRM";
    private static final String PREFERRED_CODE = "PRFR";
    private static final String OTHER_CODE = "OTH";

    private final String code;

    private NameType(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    public static NameType fromCode(String code) {
        switch ( code ) {
            case PRIMARY_CODE:
                return PRIMARY;
            case PREFERRED_CODE:
                return PREFERRED;
            default:
                return OTHER;
        }
    }
}
