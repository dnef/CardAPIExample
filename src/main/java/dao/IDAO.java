package dao;

import java.io.Serializable;
import java.util.List;

public interface IDAO<E extends Serializable> {
    List<E> getAll();
    void add(E entity);
    void remove(E entity);
    E getId(Long id);
    E getString(String string);
}
