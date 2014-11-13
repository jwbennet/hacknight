package organizer.planner.mo;

import organizer.mo.ModelObject;
import organizer.mo.common.Completable;
import organizer.mo.common.Typed;

public interface Project extends ModelObject, Completable, Typed {

    String getName();

}
