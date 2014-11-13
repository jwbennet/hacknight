package organizer.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "links")
@XmlAccessorType(XmlAccessType.NONE)
public class Links implements Decoratable {

    @XmlElementRef
    private List<Link> links;

    public Links() {
        this.links = new ArrayList<>();
    }

    public Links addLink(String rel, URI link) {
        this.links.add(new Link(rel, link));
        return this;
    }

    @Override
    public Object getJsonRepresentation() {
        Map<String, URI> links = new HashMap<>();
        getLinks().stream().forEach(link -> links.put(link.getRel(), link.getLink()));
        return links;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
