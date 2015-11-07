package ru.antowka.stock.model;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * Created by Anton Nikanorov on 21.10.15.
 */
@Entity
public class Price {

    private Long HIGH;

    private Long LOW;

    private Long OPEN;

    private Long LAST;

    private Long VALUE;

    private LocalDateTime SYSTIME;

    public LocalDateTime getSYSTIME() {
        return SYSTIME;
    }

    public void setSYSTIME(LocalDateTime SYSTIME) {
        this.SYSTIME = SYSTIME;
    }

    public Long getHIGH() {
        return HIGH;
    }

    public void setHIGH(Long HIGH) {
        this.HIGH = HIGH;
    }

    public Long getLOW() {
        return LOW;
    }

    public void setLOW(Long LOW) {
        this.LOW = LOW;
    }

    public Long getOPEN() {
        return OPEN;
    }

    public void setOPEN(Long OPEN) {
        this.OPEN = OPEN;
    }

    public Long getLAST() {
        return LAST;
    }

    public void setLAST(Long LAST) {
        this.LAST = LAST;
    }

    public Long getVALUE() {
        return VALUE;
    }

    public void setVALUE(Long VALUE) {
        this.VALUE = VALUE;
    }
}
