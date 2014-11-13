package organizer;

import java.util.List;

public interface PaginatedList<E> extends List<E>, PaginatedCollection<E> {

    PaginatedList<E> setCount(long count);

    PaginatedList<E> setLimit(int limit);

    PaginatedList<E> setOffset(int offset);
}
