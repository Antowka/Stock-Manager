package ru.antowka.stock.domain.model.price;

import lombok.Data;
import ru.antowka.stock.domain.model.ticker.Ticker;

import javax.persistence.*;
import java.util.Date;

/**
 * Price entity
 */
@Data
@Entity
public class Price {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Ticker ticker;

    private Float open;

    private Float high;

    private Float low;

    private Float close;

    private Float prevPrice;

    private Long volume;

    private Date date;
}
