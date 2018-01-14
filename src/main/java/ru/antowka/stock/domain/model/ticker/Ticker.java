package ru.antowka.stock.domain.model.ticker;

import lombok.Data;
import ru.antowka.stock.domain.model.price.Price;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Stock's tickers
 */
@Data
@Entity
@Table(name = "tickers")
public class Ticker {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String shortDescription;

    private String fullDescription;

    private String isin;

    private String boardId;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Price> prices;

    @OneToOne(targetEntity = Price.class)
    private Price lastPrice;

    private Date lastUpdatePrice;
}
