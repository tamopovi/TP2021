package vu.lt.persistence;

import vu.lt.entities.Card;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class CardsDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Card> loadAll() {
        return em.createNamedQuery("Card.findAll", Card.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Card card){
        this.em.persist(card);
    }
}
