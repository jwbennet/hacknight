package organizer.planner.mo;

import organizer.mo.ModelObject;
import organizer.mo.common.Typed;
import org.joda.time.DateTime;

public interface Log extends ModelObject, Typed {

    String getSubject();

    String getLocation();

    String getAgenda();

    DateTime getDate();

    int getDuration();

}
