package ru.antowka.stock.application.representation.transaction;

import lombok.Data;
import ru.antowka.stock.domain.model.ticker.Ticker;
import ru.antowka.stock.domain.model.transaction.TransactionType;

import java.util.Date;

/**
 * View for command
 */
@Data
public class TransactionCommand {

    private Long id;
    private String type;
    private String ticker;
    private Float price;
    private Integer amount;
    private Date date;
}
