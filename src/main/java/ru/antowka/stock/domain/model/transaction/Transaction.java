package ru.antowka.stock.domain.model.transaction;

import lombok.Data;
import ru.antowka.stock.domain.model.ticker.Ticker;

import javax.persistence.*;
import java.util.Date;

/**
 * Transaction
 */
@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private TransactionType type;

    @ManyToOne(cascade = CascadeType.ALL)
    private Ticker ticker;

    private Float price;

    private Integer amount;

    private String comment;

    private Date date;
}
