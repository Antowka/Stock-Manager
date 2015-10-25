package ru.antowka.stock.model;

import java.util.List;

/**
 * Created by Anton Nikanorov on 21.10.15.
 */
public abstract class Ticker {

    private List<Price> price;
    private String tickerName;
    private String tickerDescription;
    private String boardId;

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

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
}
