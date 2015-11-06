package ru.antowka.stock.dao;


import ru.antowka.stock.model.Ticker;
import java.time.LocalDateTime;

/**
 * Created by Anton Nik on 07.11.15.
 */
public interface TickerDao {

    /**
     * Parse prices from stock by Ticker
     *
     * @param ticker
     */
    void updatePricesForTicker(Ticker ticker);

    /**
     * Get last price from DB by Ticker
     *
     * @param ticker
     * @return Ticker
     */
    Ticker getLastPrice(Ticker ticker);

    /**
     * Get prices from DB by Ticker, Start and End dates
     *
     * @param ticker
     * @param startDate
     * @param endDate
     * @return Ticker
     */
    Ticker getPriceByDates(Ticker ticker, LocalDateTime startDate, LocalDateTime endDate);

}
