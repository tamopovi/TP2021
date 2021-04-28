package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Card;
import vu.lt.persistence.CardsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class CardView {

    @Inject
    private CardsDAO cardsDAO;

    @Getter
    @Setter
    private Card card;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer cardId = Integer.parseInt(requestParameters.get("cardId"));
        this.card = cardsDAO.findOne(cardId);
    }
}
