package ru.antowka.stock.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.antowka.stock.dao.TickerDao;
import ru.antowka.stock.model.Price;
import ru.antowka.stock.model.Ticker;
import ru.antowka.stock.utils.Json;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Anton Nikanorov on 06.11.15.
 */
@Service
public class TickerService {

    @Autowired
    private TickerDao tickerDao;

    @Autowired
    private Environment env;

    /**
     * Parse prices for ticket
     *
     * @param ticker
     */
    @Async
    public void updateTickerPrices(Ticker ticker){

        //todo - fixme
        //ticker = tickerDao.getTickerById(1);
        ticker = tickerDao.getLastPrice(ticker);
        ticker.setTickerName("GAZP");
        ticker.setBoardId("TQBR");

        LocalDateTime startDateTime = null;
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Price> newPrices = new ArrayList<Price>();

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

            try {

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");

                String url = "http://www.micex.ru/issrpc/marketdata/stock/shares/daily/short/" +
                                "result_" +
                                startDate.format(dateTimeFormatter) +
                                ".json?boardid=" +
                                ticker.getBoardId() +
                                "&secid=" +
                                ticker.getTickerName();

                JSONArray json = Json.readJsonFromUrl(url);

                JSONObject jsonPrice = (JSONObject)json.get(1);

                //create new Price from JSON
                Price price = new Price();
                price.setHigh(jsonPrice.getDouble("HIGH"));
                price.setOpen(jsonPrice.getDouble("OPEN"));
                price.setLow(jsonPrice.getDouble("LOW"));
                price.setLast(jsonPrice.getDouble("LAST"));
                price.setValue(jsonPrice.getDouble("VALUE"));

                dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime systime = LocalDateTime.parse(jsonPrice.getString("SYSTIME"), dateTimeFormatter);
                price.setSystime(systime);

                newPrices.add(price);

            } catch (Exception e) {
                e.printStackTrace();
            }


            System.out.println(startDate.toString());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

}
