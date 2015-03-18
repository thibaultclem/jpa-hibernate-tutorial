
package org.exoplatform.tutorial;

import junit.framework.TestCase;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
}
