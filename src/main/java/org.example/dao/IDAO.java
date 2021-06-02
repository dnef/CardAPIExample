package org.example.dao;

import org.example.exception.DaoException;

import java.io.Serializable;
import java.util.List;

public interface IDAO<E extends Serializable> {
    List<E> getAll() throws DaoException;

    void add(E entity) throws DaoException;

    void remove(E entity) throws DaoException;

    E getById(Long id) throws DaoException;

    E getByStringField(String stringValue) throws DaoException;

    void update(E entity) throws DaoException;
}
