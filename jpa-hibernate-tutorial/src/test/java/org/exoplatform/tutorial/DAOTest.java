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
package org.exoplatform.tutorial;

import junit.framework.TestCase;
import org.exoplatform.tutorial.dao.hibernate.TaskDAOHibernate;
import org.exoplatform.tutorial.domains.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by The eXo Platform SAS
 * Author : Thibault Clement
 * tclement@exoplatform.com
 * 3/19/15
 */
public class DAOTest extends TestCase {

    private EntityManagerFactory entityManagerFactory;

    @Override
    protected void setUp() throws Exception {
        // an EntityManagerFactory is set up once for an application
        // IMPORTANT: notice how the name here matches the name we gave the persistence-unit in persistence.xml!
        entityManagerFactory = Persistence.createEntityManagerFactory("org.exoplatform.tutorial.jpa-hibernate-tutorial");
    }

    @Override
    protected void tearDown() throws Exception {
        entityManagerFactory.close();
    }

    public void testTaskDAO() {

        EntityManager em = entityManagerFactory.createEntityManager();
        TaskDAOHibernate taskDAOHibernate = new TaskDAOHibernate(em);

        System.out.println("#### Add two Tasks");
        //Add Task
        em.getTransaction().begin();
        Task task1 = new Task("New DAO task", "nothing more to add", null, null);
        Task task2 = new Task("Another task added via DAO", "nothing more to add", null, null);
        taskDAOHibernate.persist(task1);
        taskDAOHibernate.persist(task2);
        em.getTransaction().commit();

        //Find All Task
        List<Task> tasks = taskDAOHibernate.findAll();

        System.out.println("########################################################");
        System.out.println("");
        System.out.println(" TASK LIST with " + tasks.size() + " tasks");
        System.out.println("");
        System.out.println("########################################################");
        System.out.println("");

        for ( Task task : tasks ) {
            System.out.println( "Task: " + task.getTitle() + " - " + task.getDescription());
        }

        System.out.println("");
        System.out.println("########################################################");

        System.out.println("#### Remove a Task");
        //Remove Task
        em.getTransaction().begin();
        taskDAOHibernate.remove(task2);
        em.getTransaction().commit();

        //Find All Task
        tasks = taskDAOHibernate.findAll();

        System.out.println("########################################################");
        System.out.println("");
        System.out.println(" TASK LIST with " + tasks.size() + " tasks");
        System.out.println("");
        System.out.println("########################################################");
        System.out.println("");

        for ( Task task : tasks ) {
            System.out.println( "Task: " + task.getTitle() + " - " + task.getDescription());
        }

        System.out.println("");
        System.out.println("########################################################");

        em.close();

    }

}

