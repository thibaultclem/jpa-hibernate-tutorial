package org.exoplatform.tutorial.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TClement on 3/19/15.
 */
public interface GenericDAO<E, ID extends Serializable> {

    public E find(ID id);

    public List<E> findAll();

    public void persist(E entity);

    public void remove(E entity);

}
