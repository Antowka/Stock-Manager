package ru.antowka.stock.model;

import javax.persistence.*;

/**
 * Created by Anton Nik on 12.11.15.
 */
@Entity
@Table(name = "ticker_type")
public class TickerType {

    @Id
    @GeneratedValue
    @Column(name = "ticker_type_id")
    private int tickerTypeId;

    @Column(name = "ticker_type_name")
    private String tickertypeName;

    public int getTickerTypeId() {
        return tickerTypeId;
    }

    public void setTickerTypeId(int tickerTypeId) {
        this.tickerTypeId = tickerTypeId;
    }

    public String getTickerTypeName() {
        return tickertypeName;
    }

    public void setTickertypeName(String tickertypeName) {
        this.tickertypeName = tickertypeName;
    }
}
