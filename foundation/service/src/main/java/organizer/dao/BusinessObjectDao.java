package organizer.dao;

import organizer.PaginatedCollection;
import organizer.PaginatedList;
import organizer.mo.ModelObject;
import organizer.SearchCriteria;
import organizer.bo.AbstractBusinessObject;

public interface BusinessObjectDao<M extends ModelObject, B extends AbstractBusinessObject<M>> {

    B getById(long id);

    PaginatedCollection<B> getAll();

    PaginatedCollection<B> getAll(int offset);

    PaginatedCollection<B> getAll(int offset, int limit);

    PaginatedCollection<B> getActive();

    PaginatedCollection<B> getActive(int offset);

    PaginatedCollection<B> getActive(int offset, int limit);

    PaginatedCollection<B> getInactive();

    PaginatedCollection<B> getInactive(int offset);

    PaginatedCollection<B> getInactive(int offset, int limit);

    B save(B businessObject);

    void delete(long id);

    PaginatedList<B> search(SearchCriteria criteria);
}
