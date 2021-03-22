package vu.lt.usecases;

import vu.lt.entities.Card;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import vu.lt.persistence.CardsDAO;


@Model
public class Cards implements Serializable {

    @Inject
    private CardsDAO cardsDAO;

    private Card cardToCreate = new Card();



    private List<Card> allCards;

    @PostConstruct
    public void init() {
        loadCards();
    }

    public void loadCards() {
        this.allCards = cardsDAO.loadAll();
    }

    public List<Card> getAllCards() {
        return allCards;
    }

    @Transactional
    public String createCard(){
        this.cardsDAO.persist(cardToCreate);
        return "success";
    }

    public Card getCardToCreate() {
        return cardToCreate;
    }

    public void setCardToCreate(Card cardToCreate) {
        this.cardToCreate = cardToCreate;
    }
}