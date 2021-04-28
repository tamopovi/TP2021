package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Card;
import vu.lt.entities.CardPrinting;
import vu.lt.entities.CardSet;
import vu.lt.persistence.CardPrintingsDAO;
import vu.lt.persistence.CardSetsDAO;
import vu.lt.persistence.CardsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


@Model
public class CardSets {
    @Inject
    private CardSetsDAO cardSetsDAO;

    @Inject
    private CardsDAO cardsDAO;

    @Inject
    private CardPrintingsDAO cardPrintingsDAO;

    @Getter
    @Setter
    private CardSet cardSetToCreate = new CardSet();

    @Getter
    @Setter
    private CardSet cardSet;


    @Getter
    private List<CardSet> allCardSets;


    @Getter
    List<CardSet> cardSetsWithCard;

    @Getter
    @Setter
    private CardPrinting cardPrintingToCreate = new CardPrinting();

    @Transactional
    public String createCardSet() {
        this.cardSetsDAO.persist(cardSetToCreate);
        return "success";
    }

    private void loadAllCardSets() {
        this.allCardSets = cardSetsDAO.loadAll();
    }

    public String[] getAllCardSetNames() {
        return this.allCardSets.stream().map(CardSet::getName).toArray(String[]::new);
    }

    @PostConstruct
    public void init() {
        loadAllCardSets();
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (requestParameters.get("cardSetId") != null) {
            Integer cardSetId = Integer.parseInt(requestParameters.get("cardSetId"));
            this.cardSet = cardSetsDAO.findOne(cardSetId);
        }
    }

    @Transactional
    public String createCardPrinting() {
        Card selectedCard = cardsDAO.findOne(cardPrintingToCreate.getId());
        cardPrintingToCreate.setCard(selectedCard);
        cardPrintingToCreate.setCardSet(this.cardSet);
        cardPrintingToCreate.print();
        List selectedCardPrintings = selectedCard.getCardPrintings();
        selectedCardPrintings.add(cardPrintingToCreate);
        selectedCard.setCardPrintings(selectedCardPrintings);
        cardsDAO.persist(selectedCard);
        cardSetsDAO.persist(this.cardSet);
        cardPrintingsDAO.persist(cardPrintingToCreate);
        return "success";
    }

    private void loadCardSetsWithCardId(Integer cardId) {
//        this.cardSetsWithCard =
    }
}
