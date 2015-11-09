package ru.antowka.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Anton Nikanorov on 21.10.15.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Price implements Serializable{

    private Double high;

    private Double low;

    private Double open;

    private Double last;

    private Double value;

    private LocalDateTime systime;

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
