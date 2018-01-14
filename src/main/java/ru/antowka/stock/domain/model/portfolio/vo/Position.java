package ru.antowka.stock.domain.model.portfolio.vo;

import lombok.Data;
import ru.antowka.stock.domain.model.ticker.Ticker;

import javax.persistence.*;

/**
 * Representation for position
 */
@Data
@Entity
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Ticker.class)
    private Ticker ticker;

    private Integer amount;

    private Float averagePrice;

    private Float sum;

    private Float diffPricesPercent;

    private Float averageProfit;
}
