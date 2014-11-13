package organizer;

import java.util.ArrayList;
import java.util.Collection;

public class PaginatedArrayList<E> extends ArrayList<E> implements PaginatedList<E> {

    private long count;
    private int limit;
    private int offset;

    public PaginatedArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public PaginatedArrayList() {
    }

    public PaginatedArrayList(Collection<? extends E> c) {
        super(c);
    }

    @Override
    public long getCount() {
        return count;
    }

    @Override
    public PaginatedArrayList<E> setCount(long count) {
        this.count = count;
        return this;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public PaginatedArrayList<E> setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public PaginatedArrayList<E> setOffset(int offset) {
        this.offset = offset;
        return this;
    }
}
