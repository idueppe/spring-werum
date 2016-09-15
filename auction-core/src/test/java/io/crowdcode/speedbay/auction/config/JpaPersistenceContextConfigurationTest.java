package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Person;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaPersistenceContextConfiguration.class)
public class JpaPersistenceContextConfigurationTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void entityManager() throws Exception {
        assertNotNull(em);
    }

    @Test
    @Commit
    @Transactional
    public void testCreatePerson() throws Exception {
        Person p = new Person("ingo","email");
        em.persist(p);
        p.setEmail("ingo.dueppe@crowdcode.io");
        assertNotNull(p.getId());
    }

    @Test
    @Ignore
    public void testPerson() throws Exception {
        Person p = new Person("ingo", "email");
        em.getTransaction().begin();  // vor Service
        em.persist(p);                // im Dao
        em.getTransaction().commit(); // nach Service
        em.clear();                   // nach Service (close)
        p.setEmail("ingo.dueppe@crowdcode.io"); // controller
        em.getTransaction().begin(); // vor service
        Person found = em.find(Person.class, p.getId()); // im Dao
        em.getTransaction().commit(); // nach Service
    }
}