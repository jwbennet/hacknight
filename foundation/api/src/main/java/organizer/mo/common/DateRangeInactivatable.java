package organizer.mo.common;

import org.joda.time.DateTime;

import javax.validation.constraints.AssertTrue;

public interface DateRangeInactivatable extends Inactivatable {

    DateTime getActiveFromDate();
    DateTime getActiveToDate();

    @AssertTrue(message = "The active to date must be less than the active from date")
    default boolean isValidDateRange() {
        return getActiveFromDate() == null || getActiveToDate() == null || getActiveFromDate().isBefore(getActiveToDate());
    }
}
