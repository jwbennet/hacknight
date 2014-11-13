package organizer;

import java.util.ArrayList;
import java.util.Collection;

public class ImmutablePaginatedArrayList<E> extends ArrayList<E> implements PaginatedCollection<E> {

    private final long count;
    private final int offset;
    private final int limit;

    public ImmutablePaginatedArrayList(Collection<E> list, long count, int offset, int limit) {
        super(list);
        this.count = count;
        this.offset = offset;
        this.limit = limit;
    }

    @Override
    public long getCount() {
        return count;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public int getOffset() {
        return offset;
    }
}
