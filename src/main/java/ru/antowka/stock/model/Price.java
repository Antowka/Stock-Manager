package ru.antowka.stock.model;

import java.time.LocalDateTime;

/**
 * Created by Anton Nikanorov on 21.10.15.
 */
public class Price {

    private LocalDateTime dateTime;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
