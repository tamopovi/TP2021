package vu.lt.persistence;

import vu.lt.entities.CardSet;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class CardSetsDAO {

    @PersistenceContext
    private EntityManager em;

    public List<CardSet> loadAll() {
        return em.createNamedQuery("CardSet.findAll", CardSet.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(CardSet cardSet) {
        this.em.persist(cardSet);
    }

    public CardSet findOne(Integer id) {
        return em.find(CardSet.class, id);
    }

//    public List<CardSet> findByCardId(Integer cardId){
//        return em.createNamedQuery("CardSet.findByCardId",CardSet.class).getResultList();
//    }
}
