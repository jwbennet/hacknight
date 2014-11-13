package organizer.planner.mo;

import organizer.mo.ModelObject;
import organizer.mo.common.Completable;

public interface Todo extends ModelObject, Completable {

    String getDescription();

    int getPriority();

}
