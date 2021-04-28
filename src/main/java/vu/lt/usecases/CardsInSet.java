package vu.lt.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Card;
import vu.lt.entities.CardPrinting;
import vu.lt.entities.CardSet;
import vu.lt.persistence.CardSetsDAO;
import vu.lt.persistence.CardPrintingsDAO;
import vu.lt.persistence.CardsDAO;

@Model
public class CardsInSet implements Serializable {

    @Inject
    private CardSetsDAO cardSetsDAO;

    @Inject
    private CardPrintingsDAO cardPrintingsDAO;

    @Inject
    private CardsDAO cardsDAO;


    @Getter
    @Setter
    private CardSet cardSet;

    @Getter
    @Setter
    private CardPrinting cardPrintingToCreate = new CardPrinting();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (requestParameters.get("cardSetId") != null) {
            System.out.println("THIS CARD SET IS NOT NULL");
            Integer cardSetId = Integer.parseInt(requestParameters.get("cardSetId"));
            this.cardSet = cardSetsDAO.findOne(cardSetId);
            System.out.println("FOUND CARD SET: " + cardSetsDAO.findOne(cardSetId));
            System.out.println("THIS CARD SET:" + this.cardSet);
            this.setCardSet(this.cardSet);
            System.out.println("THIS: " + this);
        }
    }

    @Transactional
    public String createCardPrinting() {
        System.out.println("THIS: " + this);
        System.out.println("createCardPrinting with cardSet: " + this.cardSet);
        cardPrintingToCreate.setCardSet(this.cardSet);
        Card selectedCard = cardsDAO.findOne(cardPrintingToCreate.getId());
        System.out.println("cardPrintingToCreate Id: " + cardPrintingToCreate.getId());
        cardPrintingToCreate.setCard(selectedCard);
        System.out.println(this.cardSet);
        cardPrintingToCreate.print();

//        cardsDAO.findCardByCardName(cardPrintingToCreate.name)


        cardPrintingsDAO.persist(cardPrintingToCreate);
        return "success";
    }
}
