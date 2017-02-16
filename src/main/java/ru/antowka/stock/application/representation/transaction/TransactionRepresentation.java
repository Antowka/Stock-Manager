package ru.antowka.stock.application.representation.transaction;

import lombok.Data;

import java.util.Date;

/**
 * View for representation
 */
@Data
public class TransactionRepresentation {

    private Long id;
    private String type;
    private String ticker;
    private Float price;
    private Integer amount;
    private Date date;
}
