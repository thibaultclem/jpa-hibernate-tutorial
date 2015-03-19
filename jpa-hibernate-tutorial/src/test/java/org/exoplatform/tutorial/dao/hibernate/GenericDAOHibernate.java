/* 
* Copyright (C) 2003-2015 eXo Platform SAS.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Affero General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Affero General Public License for more details.
*
* You should have received a copy of the GNU Affero General Public License
* along with this program. If not, see http://www.gnu.org/licenses/ .
*/
package org.exoplatform.tutorial.dao.hibernate;

import org.exoplatform.tutorial.dao.GenericDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by The eXo Platform SAS
 * Author : Thibault Clement
 * tclement@exoplatform.com
 * 3/19/15
 */
public class GenericDAOHibernate<E, ID extends Serializable> implements GenericDAO<E, ID> {


    protected Class entityClass;

    @PersistenceContext(unitName = "org.exoplatform.tutorial.jpa-hibernate-tutorial")
    protected EntityManager entityManager;

    public GenericDAOHibernate() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[0];
    }

    //Only for test purpose
    public GenericDAOHibernate(EntityManager em) {
        entityManager =em;
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public E find(ID id) {
        return (E) entityManager.find(entityClass, id);
    }

    @Override
    public List<E> findAll() {

        //Create a Criteria Query
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> q = cb.createQuery(entityClass);
        Root<E> e = q.from(entityClass);
        q.select(e);

        TypedQuery<E> query = entityManager.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void persist(E entity) {
        entityManager.persist(entity);
    }

    @Override
    public void remove(E entity) {
        entityManager.remove(entity);
    }
}

