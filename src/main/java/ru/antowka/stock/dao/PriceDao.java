package ru.antowka.stock.dao;

import org.springframework.stereotype.Repository;
import ru.antowka.stock.model.Price;
import ru.antowka.stock.model.Ticker;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Anton Nik on 06.11.15.
 */
@Repository
public class PriceDao {

    public List<Price> getPriceByLink(Ticker ticker, LocalDateTime startDate, LocalDateTime endDate){

    }

    public List<Ticker> getTickers(){

    }
}
