package organizer.jaxrs.providers;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "errorCode")
@XmlType(name = "ErrorCodeType")
@XmlEnum
public enum ErrorCode {
    @XmlEnumValue(ErrorCode.UNKNOWN_CODE) UNKNOWN(ErrorCode.UNKNOWN_CODE),
    @XmlEnumValue(ErrorCode.RESULT_NOT_FOUND_CODE) RESULT_NOT_FOUND(ErrorCode.RESULT_NOT_FOUND_CODE),
    @XmlEnumValue(ErrorCode.NOT_ACCEPTABLE_CODE) NOT_ACCEPTABLE(ErrorCode.NOT_ACCEPTABLE_CODE),
    @XmlEnumValue(ErrorCode.ILLEGAL_ARGUMENTS_CODE) ILLEGAL_ARGUMENTS(ErrorCode.ILLEGAL_ARGUMENTS_CODE),
    @XmlEnumValue(ErrorCode.VALIDATION_FAILURE_CODE) VALIDATION_FAILURE(ErrorCode.VALIDATION_FAILURE_CODE),
    @XmlEnumValue(ErrorCode.HANDLER_NOT_FOUND_CODE) HANDLER_NOT_FOUND(ErrorCode.HANDLER_NOT_FOUND_CODE);

    private static final String UNKNOWN_CODE = "10000";
    private static final String RESULT_NOT_FOUND_CODE = "10001";
    private static final String NOT_ACCEPTABLE_CODE = "10002";
    private static final String ILLEGAL_ARGUMENTS_CODE = "10003";
    private static final String VALIDATION_FAILURE_CODE = "10004";
    private static final String HANDLER_NOT_FOUND_CODE = "10005";

    private final String code;

    private ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ErrorCode fromCode(String code) {
        switch(code) {
            case RESULT_NOT_FOUND_CODE:
                return RESULT_NOT_FOUND;
            case NOT_ACCEPTABLE_CODE:
                return NOT_ACCEPTABLE;
            case ILLEGAL_ARGUMENTS_CODE:
                return ILLEGAL_ARGUMENTS;
            case VALIDATION_FAILURE_CODE:
                return VALIDATION_FAILURE;
            case HANDLER_NOT_FOUND_CODE:
                return HANDLER_NOT_FOUND;
            default:
                return UNKNOWN;
        }
    }
}
