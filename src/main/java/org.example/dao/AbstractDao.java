package org.example.dao;

import org.example.exception.DaoException;

import java.io.Serializable;
import java.util.List;

public class AbstractDao<E extends Serializable> implements IDAO<E>{


    @Override
    public List<E> getAll() {
        return null;
    }

    @Override
    public void add(E entity) {

    }

    @Override
    public void remove(E entity) {

    }

    @Override
    public E getById(Long id){
        return null;
    }

    @Override
    public E getByStringField(String stringValue) {
        return null;
    }

    @Override
    public void update(E entity) {

    }
}
