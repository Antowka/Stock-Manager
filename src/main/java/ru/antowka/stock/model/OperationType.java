package ru.antowka.stock.model;

import javax.persistence.*;

/**
 * Created by Anton Nik on 27.11.15.
 */
@Entity
@Table(name = "operation_type")
public class OperationType {

    @Id
    @GeneratedValue
    @Column(name = "operation_type_id")
    private int tickerTypeId;

    @Column(name = "operation_type_name")
    private String tickerTypeName;

    public String getTickerTypeName() {
        return tickerTypeName;
    }

    public void setTickerTypeName(String tickerTypeName) {
        this.tickerTypeName = tickerTypeName;
    }

    public int getTickerTypeId() {
        return tickerTypeId;
    }

    public void setTickerTypeId(int tickerTypeId) {
        this.tickerTypeId = tickerTypeId;
    }
}
