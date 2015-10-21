package ru.antowka.stock.model;

import java.util.List;

/**
 * Created by Anton Nikanorov on 21.10.15.
 */
public abstract class Ticker {

    private List<Price> price;
    private String tickerName;
    private String tickerDescription;
    private Float nominalPrice;
    private Float amountInPortfolio;
    private String boardId;
    private Float amountEmission;

    public List<Price> getPrice() {
        return price;
    }

    public void setPrice(List<Price> price) {
        this.price = price;
    }

    public String getTickerName() {
        return tickerName;
    }

    public void setTickerName(String tickerName) {
        this.tickerName = tickerName;
    }

    public String getTickerDescription() {
        return tickerDescription;
    }

    public void setTickerDescription(String tickerDescription) {
        this.tickerDescription = tickerDescription;
    }

    public Float getNominalPrice() {
        return nominalPrice;
    }

    public void setNominalPrice(Float nominalPrice) {
        this.nominalPrice = nominalPrice;
    }

    public Float getAmountInPortfolio() {
        return amountInPortfolio;
    }

    public void setAmountInPortfolio(Float amountInPortfolio) {
        this.amountInPortfolio = amountInPortfolio;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public Float getAmountEmission() {
        return amountEmission;
    }

    public void setAmountEmission(Float amountEmission) {
        this.amountEmission = amountEmission;
    }
}
