package edu.caece.app.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository(value = "personaDao")
public class JPAPersonaDao implements IPersonaDao {

    private EntityManager em = null;

    @PersistenceContext
    public void setEntityManager(EntityManager em) throws Exception {
        this.em = em;
    }

}