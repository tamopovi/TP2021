package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "CARDPRINTING")
public class CardPrinting {
    public CardPrinting() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CARD_ID")
    private Card card;


    @ManyToOne
    @JoinColumn(name = "CARDSET_ID")
    private CardSet cardSet;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardPrinting cardPrinting = (CardPrinting) o;
        return Objects.equals(id, cardPrinting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void print(){
        System.out.println("id: " + this.id);
        System.out.println("card name: " + this.card.getName());
        System.out.println("card set name: " + this.cardSet.getName());
    }
}
