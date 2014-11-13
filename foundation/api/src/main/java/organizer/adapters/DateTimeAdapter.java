package organizer.adapters;

import org.joda.time.DateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateTimeAdapter extends XmlAdapter<String, DateTime> {

    @Override
    public DateTime unmarshal(String dateString) throws Exception {
        return new DateTime(dateString);
    }

    @Override
    public String marshal(DateTime date) throws Exception {
        return date.toString();
    }
}
