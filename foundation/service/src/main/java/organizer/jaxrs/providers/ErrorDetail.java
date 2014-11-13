package organizer.jaxrs.providers;

import javax.xml.bind.annotation.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {
        ErrorDetail.Elements.CODE,
        ErrorDetail.Elements.MESSAGES,
        ErrorDetail.Elements.DETAIL,
        ErrorDetail.Elements.MORE_INFO
})
public class ErrorDetail {

    @XmlElement(name = Elements.CODE)
    private ErrorCode code;
    @XmlElementWrapper(name = Elements.MESSAGES)
    @XmlElement(name = Elements.MESSAGE)
    private List<String> messages;
    @XmlElement(name = Elements.DETAIL)
    private String detail;
    @XmlElement(name = Elements.MORE_INFO)
    private URI moreInfo;

    public ErrorDetail addMessage(String message) {
        if(this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(message);
        return this;
    }

    public ErrorDetail clearMessages() {
        if(this.messages != null) {
            this.messages.clear();
        }
        return this;
    }

    public List<String> getMessages() {
        return messages;
    }

    public ErrorDetail setMessages(List<String> messages) {
        this.messages = messages;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public ErrorDetail setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public ErrorCode getCode() {
        return code;
    }

    public ErrorDetail setCode(ErrorCode code) {
        this.code = code;
        return this;
    }

    public URI getMoreInfo() {
        return moreInfo;
    }

    public ErrorDetail setMoreInfo(URI moreInfo) {
        this.moreInfo = moreInfo;
        return this;
    }

    public static final class Elements {

        public static final String CODE = "code";
        public static final String MESSAGES = "messages";
        public static final String MESSAGE = "message";
        public static final String DETAIL = "detail";
        public static final String MORE_INFO = "moreInfo";

        private Elements() {
            throw new UnsupportedOperationException("Private constructor should never be invoked");
        }
    }
}
