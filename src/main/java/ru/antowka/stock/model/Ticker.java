package ru.antowka.stock.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by Anton Nikanorov on 21.10.15.
 */
@Entity
@Table(name = "ticker")
public class Ticker implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ticker_id")
    private int tickerId;

    @OneToMany(targetEntity=Price.class, mappedBy="tickerId", fetch=FetchType.EAGER)
    private List<Price> price;

    @Column(name = "ticker_type")
    private String tickerType;

    @Column(name = "ticker_name")
    private String tickerName;

    @Column(name = "ticker_description")
    private String tickerDescription;

    @Column(name = "board_id")
    private String boardId;

    public String getTickerType() {
        return tickerType;
    }

    public void setTickerType(String ticker_type) {
        this.tickerType = ticker_type;
    }

    public int getTickerId() {
        return tickerId;
    }

    public void setTickerId(int tickerId) {
        this.tickerId = tickerId;
    }

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

    /**
     * Get last price in current range
     *
     * @return
     */
    public Price getLastTick(){
        Optional<Price> priceLast = price.stream().reduce((priceA, priceZ) -> priceZ);
        return priceLast.get();
    }
}
