package ru.antowka.stock.model;

import javax.persistence.*;

/**
 * Created by Anton Nikanorov on 21.10.15.
 */
@Entity
@Table(name = "operations")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private int positionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ticker_id", nullable = false)
    private Ticker ticker;

    @Column(name = "amount")
    private int amount;

    @Column(name = "avrg_price")
    private double avrgPrice;


    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getAvrgPrice() {
        return avrgPrice;
    }

    public void setAvrgPrice(double avrgPrice) {
        this.avrgPrice = avrgPrice;
    }
}
