package organizer.service;

import organizer.PaginatedCollection;
import organizer.PaginatedList;
import organizer.mo.ModelObject;
import organizer.SearchCriteria;

public interface ModelObjectService<M extends ModelObject> {

    M getById(long id);

    PaginatedCollection<M> getAll();

    PaginatedCollection<M> getAll(int offset);

    PaginatedCollection<M> getAll(int offset, int limit);

    PaginatedCollection<M> getActive();

    PaginatedCollection<M> getActive(int offset);

    PaginatedCollection<M> getActive(int offset, int limit);

    PaginatedCollection<M> getInactive();

    PaginatedCollection<M> getInactive(int offset);

    PaginatedCollection<M> getInactive(int offset, int limit);

    M create(M modelObject);

    M update(Long id, M modelObject);

    void delete(Long id);

    PaginatedList<M> search(SearchCriteria criteria);

}
