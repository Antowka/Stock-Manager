package ru.antowka.stock.domain.model.portfolio.vo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representation for position
 */
@Data
@Entity
@Table(name = "positions")
public class Position {

    @Id
    private String ticker;
    private Integer amount;
    private Float averagePrice;
    private Float sum;
}
