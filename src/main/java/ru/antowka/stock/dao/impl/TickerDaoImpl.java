package ru.antowka.stock.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.antowka.stock.dao.TickerDao;
import ru.antowka.stock.model.Price;
import ru.antowka.stock.model.Ticker;
import ru.antowka.stock.utils.Json;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton Nik on 07.11.15.
 */
@Repository
public class TickerDaoImpl implements TickerDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Price parsPricesForTicker(Ticker ticker, LocalDateTime date) {


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateInString = date.toLocalDate().format(dateTimeFormatter);

        String url = "http://www.micex.ru/issrpc/marketdata/stock/" +
                        ticker.getTickerType() +
                        "/history/short/result.json?boardid=" +
                        ticker.getBoardId() +
                        "&secid=" +
                        ticker.getTickerName() +
                        "&date=" +
                        dateInString;

        JSONObject jsonPrice = null;

        try {

            JSONArray json = Json.readJsonFromUrl(url);
            jsonPrice = (JSONObject)json.get(1);
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }


        //create new Price from JSON
        Price price = new Price();
        price.setHigh(jsonPrice.getDouble("HIGH"));
        price.setOpen(jsonPrice.getDouble("OPEN"));
        price.setLow(jsonPrice.getDouble("LOW"));
        price.setLast(jsonPrice.getDouble("WAPRICE"));
        price.setValue(jsonPrice.getDouble("VALUE"));
        price.setSystime(date);

        return price;
    }

    @Override
    @Transactional
    public Ticker getTickerById(int tickerId) {

        return (Ticker)sessionFactory
                .getCurrentSession()
                .get(Ticker.class, tickerId);
    }

    @Override
    @Transactional
    public Ticker getLastPrice(Ticker ticker) {

        List<Price> prices = new ArrayList<>();
        //todo - fix this request
        prices.add(
                (Price) sessionFactory
                        .getCurrentSession()
                        .createCriteria(Price.class)
                        .add(Restrictions.eq("tickerId", ticker.getTickerId()))
                        .addOrder(Order.desc("datetime"))
                        .uniqueResult()
        );

        ticker.setPrice(prices);

        return ticker;
    }

    @Override
    @Transactional
    public Ticker getPriceByDates(Ticker ticker, LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }
}
