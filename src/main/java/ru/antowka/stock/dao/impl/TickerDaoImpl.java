package ru.antowka.stock.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.antowka.stock.dao.TickerDao;
import ru.antowka.stock.model.Price;
import ru.antowka.stock.model.Ticker;
import ru.antowka.stock.model.factory.MyBeanFactory;
import ru.antowka.stock.utils.JsonUtils;

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

    private Logger logger = Logger.getLogger(TickerDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MyBeanFactory myBeanFactory;

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public void parsPricesForTicker(Ticker ticker, LocalDateTime date) {


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateInString = date.toLocalDate().format(dateTimeFormatter);

        String url = "http://www.micex.ru/issrpc/marketdata/stock/" +
                        ticker.getTickerTypeId().getTickerTypeName() +
                        "/history/short/result.json?boardid=" +
                        ticker.getBoardId() +
                        "&secid=" +
                        ticker.getTickerName() +
                        "&date=" +
                        dateInString;

        try {

            JSONArray json = JsonUtils.readJsonFromUrl(url);
            JSONObject jsonPrice = (JSONObject)json.get(1);

            //create new Price from JSON
            Price price = myBeanFactory.getNewPrice();
            price.setHigh(jsonPrice.getDouble("HIGH"));
            price.setOpen(jsonPrice.getDouble("OPEN"));
            price.setLow(jsonPrice.getDouble("LOW"));
            price.setLast(jsonPrice.getDouble("WAPRICE"));
            price.setValue(jsonPrice.getDouble("VALUE"));
            price.setTicker(ticker);
            price.setSystime(date);

            sessionFactory.getCurrentSession().saveOrUpdate(price);


        } catch (IOException | JSONException e) {

            logger.error("Can't get json for parsing! URL: " + url);

        } catch (HibernateException e){

            logger.error("Can't save price to DB!", e);
        }
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

        prices.add((Price) sessionFactory
                .getCurrentSession()
                .createCriteria(Price.class)
                .add(Restrictions.eq("ticker.tickerId", ticker.getTickerId()))
                .addOrder(Order.desc("systime"))
                .setMaxResults(1)
                .uniqueResult());

        ticker.setPrice(prices);

        return ticker;
    }

    @Override
    @Transactional
    public Ticker getPriceByDates(Ticker ticker, LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Ticker> getAllTickets() {

        return (List<Ticker>)sessionFactory.getCurrentSession()
                    .createCriteria(Ticker.class)
                    .list();
    }


}
