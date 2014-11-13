package organizer.identity;

import organizer.identity.mo.AddressType;
import organizer.identity.dto.AddressDto;
import organizer.identity.dto.PersonDto;
import organizer.identity.mo.ContactType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import org.joda.time.DateTime;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class DtoTest {

    public static void main(String[] args) throws Exception {
        testPersonDto();
        System.out.println("\n\n\n\n");
        testAddressDto();
    }

    public static void testPersonDto() throws Exception {
        PersonDto.Builder personBuilder = PersonDto.Builder.create();
        PersonDto person = personBuilder.setUsername("jawbenne")
                .setId(1L)
                .setTenantId(1L)
                .setCreationDate(DateTime.now())
                .setLastUpdateDate(DateTime.now()).build();
        JAXBContext jaxbContext = JAXBContext.newInstance(PersonDto.class);
        Marshaller xmlMarshaller = jaxbContext.createMarshaller();
        xmlMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        System.out.println("As XML:");
        xmlMarshaller.marshal(person, System.out);

        System.out.println("\n\nAs JSON:");
        ObjectMapper jsonMarshaller = new ObjectMapper();
        jsonMarshaller.enable(SerializationFeature.INDENT_OUTPUT);
        jsonMarshaller.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        AnnotationIntrospector primaryIntrospector = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
        AnnotationIntrospector secondaryIntrospector = new JacksonAnnotationIntrospector();
        AnnotationIntrospector pair = AnnotationIntrospector.pair(primaryIntrospector, secondaryIntrospector);
        jsonMarshaller.setAnnotationIntrospector(pair);
        System.out.println(jsonMarshaller.writeValueAsString(person));
    }

    public static void testAddressDto() throws Exception {
        AddressDto.Builder addressBuilder = AddressDto.Builder.create();
        AddressDto address = addressBuilder.setAddressLine1("4104 W Dresden Drive")
                .setCity("Bloomington")
                .setState("IN")
                .setPostalCode("47404")
                .setType(AddressType.OTHER)
                .setId(1L)
                .setTenantId(1L)
                .setContactType(ContactType.PERSON)
                .setContactId(1L)
                .setCreationDate(DateTime.now())
                .setLastUpdateDate(DateTime.now()).build();
        JAXBContext jaxbContext = JAXBContext.newInstance(AddressDto.class);
        Marshaller xmlMarshaller = jaxbContext.createMarshaller();
        xmlMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        System.out.println("As XML:");
        xmlMarshaller.marshal(address, System.out);

        System.out.println("\n\nAs JSON:");
        ObjectMapper jsonMarshaller = new ObjectMapper();
        jsonMarshaller.enable(SerializationFeature.INDENT_OUTPUT);
        jsonMarshaller.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        AnnotationIntrospector primaryIntrospector = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
        AnnotationIntrospector secondaryIntrospector = new JacksonAnnotationIntrospector();
        AnnotationIntrospector pair = AnnotationIntrospector.pair(primaryIntrospector, secondaryIntrospector);
        jsonMarshaller.setAnnotationIntrospector(pair);
        System.out.println(jsonMarshaller.writeValueAsString(address));
    }
}
