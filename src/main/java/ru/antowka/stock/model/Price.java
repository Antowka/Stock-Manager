package ru.antowka.stock.model;

import org.hibernate.annotations.Type;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Anton Nikanorov on 21.10.15.
 */
@Entity
@Component
@Table(name = "price")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Price implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id", updatable = false, nullable = false)
    private int priceId;

    @ManyToOne
    @JoinColumn(name = "ticker_id")
    private Ticker ticker;

    @Column(name = "price_high")
    private Double high;

    @Column(name = "price_low")
    private Double low;

    @Column(name = "price_open")
    private Double open;

    @Column(name = "price_close")
    private Double last;

    @Column(name = "value")
    private Double value;

    @Column(name = "datetime", columnDefinition="TIMESTAMP")
    private LocalDateTime systime;

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

    public LocalDateTime getSystime() {
        return systime;
    }

    public void setSystime(LocalDateTime systime) {

        this.systime = systime;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
