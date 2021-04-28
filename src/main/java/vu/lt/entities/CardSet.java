package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "CardSet.findAll", query = "select cs from CardSet as cs")
})
@Table(name = "CARDSET")
@Getter
@Setter
public class CardSet {

    public CardSet() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CARDSET_ID")
    private Integer id;

    private String name;

    @Size(max = 3)
    @Column(name = "CODE")
    private String code;
    private Integer yearOfRelease;
    private Integer size;

    @OneToMany(mappedBy = "cardSet", fetch = FetchType.EAGER)
    private List<CardPrinting> cardPrintings = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardSet cardSet = (CardSet) o;
        return Objects.equals(name, cardSet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
