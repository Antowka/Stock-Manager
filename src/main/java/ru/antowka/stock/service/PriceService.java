package ru.antowka.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import ru.antowka.stock.dao.PriceDao;
import ru.antowka.stock.model.Price;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Anton Nikanorov on 06.11.15.
 */
public class PriceService {

    @Autowired
    private PriceDao priceDao;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime currentDateTime;

    public List<Price> loadNewPrices(){

        priceDao.getTickers().stream().forEach(ticker -> {

            //Save new prices to Object
            ticker.setPrice(
                    priceDao.getPriceByLink(ticker, ticker.getLastTick().getDateTime(), currentDateTime)
            );

            //Update object Ticker to DB
            
        });
    }
}
