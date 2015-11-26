package ru.antowka.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.antowka.stock.dao.SettingDao;
import ru.antowka.stock.dao.TickerDao;
import ru.antowka.stock.model.Price;
import ru.antowka.stock.model.Setting;
import ru.antowka.stock.model.Ticker;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * Created by Anton Nikanorov on 06.11.15.
 */
@Service
public class TickerService {

    @Autowired
    private TickerDao tickerDao;

    @Autowired
    private SettingDao settingDao;

    @Autowired
    private Environment env;



    /**
     * Parse prices for ticket
     *
     * @param ticker
     */
    public void updatePricesByTicker(Ticker ticker){

        ticker = tickerDao.getLastPrice(ticker);

        LocalDateTime startDateTime = null;
        LocalDateTime currentDateTime = LocalDateTime.now();

        //check on exist prices in List
        List<Price> prices = ticker.getPrice();

        if(!prices.isEmpty() && prices.get(0) != null) {

            startDateTime = prices.get(0).getSystime();
        }else{

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(env.getProperty("main.dateTimeFormat"));
            startDateTime = LocalDateTime.parse(env.getProperty("main.startHistoryDate"), dateTimeFormatter);
        }

        //iterate dates range
        for(LocalDateTime startDate = startDateTime; startDate.isBefore(currentDateTime); startDate = startDate.plusDays(1)) {

            //filter weekend days
            if(startDate.getDayOfWeek() != DayOfWeek.SATURDAY && startDate.getDayOfWeek() != DayOfWeek.SUNDAY){

                //save new price
                tickerDao.parsPricesForTicker(ticker, startDate.plusHours(23).plusMinutes(59));

                //timeout between request
                try {

                    Thread.sleep(3000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    /**
     * Update all data for Ticker
     *
     */
    @Async
    @Scheduled(cron="0 0 1 * * MON-FRI")
    public void updateAllTickers(){

        Setting setting = settingDao.getSettingByName("price_updating_now");

        if("0".equals(setting.getValue())) {

            //set flag about - updating
            setting.setValue("1");
            settingDao.updateSetting(setting);

            //updating
            tickerDao.getAllTickets().stream().forEach(this::updatePricesByTicker);

            //set flag about - update finished
            setting.setValue("0");
            settingDao.updateSetting(setting);
        }
    }
}
