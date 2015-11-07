package ru.antowka.stock.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.antowka.stock.dao.TickerDao;
import ru.antowka.stock.model.Price;
import ru.antowka.stock.model.Ticker;

import java.net.URL;
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

        //check on exist prices in List
        List<Price> prices = ticker.getPrice();

        if(!prices.isEmpty() && prices.get(0) != null) {

            startDateTime = prices.get(0).getSYSTIME();
        }else{

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(env.getProperty("main.dateTimeFormat"));
            startDateTime = LocalDateTime.parse(env.getProperty("main.startHistoryDate"), dateTimeFormatter);
        }

        //iterate dates range
        for(LocalDateTime startDate = startDateTime; startDate
                .isBefore(currentDateTime); startDate = startDate.plusDays(1)) {

            try {

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");

                URL url = new URL("http://www.micex.ru/issrpc/marketdata/stock/shares/daily/short/" +
                        "result_" +
                        startDate.format(dateTimeFormatter) +
                        ".json?boardid=" +
                        ticker.getBoardId() +
                        "&secid=" +
                        ticker.getTickerName()
                );

                ObjectMapper mapper = new ObjectMapper();
                
                List<Price> price = mapper.readValue(url, new TypeReference<List<Price>>(){});
                String test = "";

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
