package organizer.mo.common;

import org.joda.time.DateTime;

public interface Inactivatable {

    boolean isActive();

    boolean isActive(DateTime activeAsOfDate);
}
