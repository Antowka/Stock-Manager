package ru.antowka.stock.domain.model.ticker;

import lombok.Data;

import javax.persistence.*;

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
}
