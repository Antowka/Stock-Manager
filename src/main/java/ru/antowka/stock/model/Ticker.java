package ru.antowka.stock.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Anton Nikanorov on 21.10.15.
 */
@Entity
@Table(name = "ticker")
public class Ticker implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticker_id")
    private int tickerId;

    @JsonIgnore
    @OneToMany(targetEntity=Price.class, mappedBy="ticker", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Price> price;

    @ManyToOne
    @JoinColumn(name = "ticker_type_id", nullable = false)
    private TickerType tickerTypeId;

    @Column(name = "ticker_name")
    private String tickerName;

    @Column(name = "ticker_description")
    private String tickerDescription;

    @Column(name = "board_id")
    private String boardId;

    @JsonIgnore
    @OneToMany(targetEntity=Operation.class, mappedBy="ticker", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Operation> operations;

    public TickerType getTickerTypeId() {
        return tickerTypeId;
    }

    public void setTickerTypeId(TickerType ticker_type) {
        this.tickerTypeId = ticker_type;
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

    public Set<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }
}
