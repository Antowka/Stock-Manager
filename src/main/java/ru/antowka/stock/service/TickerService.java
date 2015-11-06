package ru.antowka.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import ru.antowka.stock.dao.TickerDao;
import ru.antowka.stock.model.Ticker;

import java.time.LocalDateTime;


/**
 * Created by Anton Nikanorov on 06.11.15.
 */
@Service
public class TickerService {

    @Autowired
    private TickerDao tickerDao;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime currentDateTime;

    public void updateTickerPrices(Ticker ticker){

        ticker = tickerDao.getLastPrice(ticker);
        String test = "";
    }

}
