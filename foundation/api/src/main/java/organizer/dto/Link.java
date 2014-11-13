package organizer.dto;

import javax.xml.bind.annotation.*;
import java.net.URI;

@XmlRootElement(name = "link")
@XmlAccessorType(XmlAccessType.NONE)
public class Link {

    @XmlAttribute(name = "rel")
    private String rel;
    @XmlValue
    private URI link;

    public Link() { }

    public Link(String rel, URI link) {
        this.rel = rel;
        this.link = link;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public URI getLink() {
        return link;
    }

    public void setLink(URI link) {
        this.link = link;
    }
}
