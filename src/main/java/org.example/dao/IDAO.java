package org.example.dao;

import java.io.Serializable;
import java.util.List;

public interface IDAO<E extends Serializable> {
    List<E> getAll();

    void add(E entity);

    void remove(E entity);

    E getById(Long id);

    E getByStringField(String stringValue);

    void update(E entity);
}
