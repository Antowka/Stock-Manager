package ru.antowka.stock.application.representation.position;

import lombok.Data;

/**
 * Representation for Position
 */
@Data
public class PositionRepresentation {

    private String ticker;
    private Integer amount;
    private Float averagePrice;
    private Float lastMarketPrice;
    private Float sum;
    private Float diffPricesPercent;
    private Float averageProfit;
}
