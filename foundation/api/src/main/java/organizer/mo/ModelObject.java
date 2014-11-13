package organizer.mo;

import organizer.mo.common.Auditable;
import organizer.mo.common.DateRangeInactivatable;
import organizer.mo.common.Identifiable;
import organizer.mo.common.Scopable;

import java.io.Serializable;

public interface ModelObject extends Auditable, DateRangeInactivatable, Identifiable, Scopable, Serializable {

}
