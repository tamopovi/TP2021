package vu.lt.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import vu.lt.Enums.Color;
import vu.lt.Enums.Type;

import lombok.Getter;
import lombok.Setter;


@Entity
@NamedQueries({
        @NamedQuery(name = "Card.findAll", query = "select a from Card as a")
})
@Table(name = "CARD")
@Getter
@Setter
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARD_ID")
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Enumerated(EnumType.STRING)
    private Color color;
    @Enumerated(EnumType.STRING)
    private Type type;

    @Min(value = 0)
    private Integer manaValue;

    @OneToMany(mappedBy = "card", fetch = FetchType.EAGER)
    private List<CardPrinting> cardPrintings = new ArrayList<>();


    public Card() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id) &&
                Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}