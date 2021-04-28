package vu.lt.persistence;

import vu.lt.entities.CardPrinting;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class CardPrintingsDAO {
    @PersistenceContext
    private EntityManager em;

    public void persist(CardPrinting cp) {
        this.em.persist(cp);
    }
}
