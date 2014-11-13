package organizer.mo.common;

import org.joda.time.DateTime;

public interface Auditable {

    DateTime getCreationDate();

    DateTime getLastUpdateDate();
}
