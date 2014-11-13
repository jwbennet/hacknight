package organizer;

import java.util.Collection;

public interface PaginatedCollection<E> extends Collection<E> {

    long getCount();

    int getLimit();

    int getOffset();
}
