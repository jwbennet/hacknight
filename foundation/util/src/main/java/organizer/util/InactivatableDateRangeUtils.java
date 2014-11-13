package organizer.util;

import org.joda.time.DateTime;

public final class InactivatableDateRangeUtils {

    private InactivatableDateRangeUtils() {
        throw new UnsupportedOperationException("This is a private library method which should not be invoked directly");
    }

    public static boolean isActive(DateTime activeFromDate, DateTime activeToDate, DateTime activeAsOfDate) {
        if( activeAsOfDate == null ) {
            activeAsOfDate = DateTime.now();
        }
        return computeActive(activeFromDate, activeToDate, activeAsOfDate);
    }

    private static boolean computeActive(DateTime activeFromDate, DateTime activeToDate, DateTime activeAsOfDate) {
        // the precision of this check should be to the second, not milliseconds, so we want to chop off any
        // milliseconds and do a ceiling of our seconds. Sometimes changes are made in near real time after a record
        // becomes activated or inactivated so we want to have the best result possible if they are still within the
        // same second, so we essentially always round up to ensure that this check will never fail in high throughput
        // environments
        activeAsOfDate = activeAsOfDate.secondOfDay().roundCeilingCopy();
        return ( activeFromDate == null || activeAsOfDate.getMillis() >= activeFromDate.getMillis() ) &&
                ( activeToDate == null || activeAsOfDate.getMillis() < activeToDate.getMillis() );
    }
}
