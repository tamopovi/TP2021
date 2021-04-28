package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Card;
import vu.lt.persistence.CardsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Cards {
    @Inject
    private CardsDAO cardsDAO;

    @Getter
    @Setter
    private Card cardToCreate = new Card();

    @Getter
    private List<Card> allCards;

    @PostConstruct
    public void init() {
        loadAllCards();
    }

    @Transactional
    public String createCard() {
        this.cardsDAO.persist(cardToCreate);
        return "success";
    }

    private void loadAllCards() {
        this.allCards = cardsDAO.loadAll();
    }
}
