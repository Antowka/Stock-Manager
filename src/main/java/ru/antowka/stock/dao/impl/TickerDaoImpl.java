package ru.antowka.stock.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.antowka.stock.dao.TickerDao;
import ru.antowka.stock.model.Price;
import ru.antowka.stock.model.Ticker;

import java.time.LocalDateTime;
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
    public void updatePricesForTicker(Ticker ticker) {

    }

    @Override
    @Transactional
    public Ticker getLastPrice(Ticker ticker) {

        List<Price> prices = new ArrayList<>();

        try {
            prices.add(
                    (Price) sessionFactory
                            .getCurrentSession()
                            .createCriteria(Price.class)
                            .add(Restrictions.eq("tickerId", ticker.getTickerId()))
                            .addOrder(Order.desc("datetime"))
                            .uniqueResult()
            );

            ticker.setPrice(prices);
        }catch(Exception e) {
            e.printStackTrace();
        }

        return ticker;
    }

    @Override
    @Transactional
    public Ticker getPriceByDates(Ticker ticker, LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }
}
