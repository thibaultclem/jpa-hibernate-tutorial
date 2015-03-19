
package org.exoplatform.tutorial;

import junit.framework.TestCase;
import org.exoplatform.tutorial.domains.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EntityManagerTest extends TestCase {
    private EntityManagerFactory entityManagerFactory;

    @Override
    protected void setUp() throws Exception {
        // an EntityManagerFactory is set up once for an application
        // IMPORTANT: notice how the name here matches the name we gave the persistence-unit in persistence.xml!
        entityManagerFactory = Persistence.createEntityManagerFactory( "org.exoplatform.tutorial.jpa-hibernate-tutorial" );
    }

    @Override
    protected void tearDown() throws Exception {
        entityManagerFactory.close();
    }

    public void testEntityManagerFactoryNotNull() {

        assertNotNull(entityManagerFactory);
    }

    public void testCreationTask() {

        //Add some tasks in DB
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //Start transaction
        entityManager.getTransaction().begin();

        //Add 5 Tasks in DB
        entityManager.persist( new Task("go to shopping", "to buy milk", null, null));
        entityManager.persist( new Task("go to bank", "to withdrawal money", null, null));
        entityManager.persist( new Task("Stop working", "No ! Work harder", null, null));
        entityManager.persist( new Task("Another task", "I don't know why...", null, null));
        entityManager.persist( new Task("PL Meeting", "let's talk hibernate", null, null));

        //Commit
        entityManager.getTransaction().commit();
        //Close Transaction
        entityManager.close();

        // Get Task and display them
        entityManager = entityManagerFactory.createEntityManager();
        //Start transaction
        entityManager.getTransaction().begin();
        //Queris all tasks from DB
        List<Task> result = entityManager.createQuery( "from Task", Task.class ).getResultList();

        System.out.println("########################################################");
        System.out.println("");
        System.out.println(" TASK LIST");
        System.out.println("");
        System.out.println("########################################################");
        System.out.println("");

        for ( Task task : result ) {
            System.out.println( "Task: " + task.getTitle() + " - " + task.getDescription());
        }

        System.out.println("");
        System.out.println("########################################################");

        //commit
        entityManager.getTransaction().commit();
        //Close Transaction
        entityManager.close();
    }
}
