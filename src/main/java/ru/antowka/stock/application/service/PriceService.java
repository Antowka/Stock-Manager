package ru.antowka.stock.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.antowka.stock.domain.model.price.Price;
import ru.antowka.stock.domain.model.ticker.Ticker;
import ru.antowka.stock.infrastructure.spring.repository.PriceRepository;
import ru.antowka.stock.infrastructure.spring.repository.TickerRepository;
import ru.antowka.stock.infrastructure.utils.JsonUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by anton on 23.02.17.
 */
@Service
public class PriceService {

    private PriceRepository priceRepository;
    private TickerRepository tickerRepository;
    private static final String url = "http://www.micex.ru/issrpc/marketdata/stock/shares/daily/short/result.json?boardid=[BOARD_ID]&secid=[TICKER_ID]";

    @Autowired
    public void setPriceRepository(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Autowired
    public void setTickerRepository(TickerRepository tickerRepository) {
        this.tickerRepository = tickerRepository;
    }

    @Scheduled(fixedDelay=15000)
    public void loadPrices() {

        List<Ticker> tickers = tickerRepository.findAll();

        for (Ticker ticker : tickers) {

            String directUrl = url
                    .replace("[BOARD_ID]", ticker.getBoardId())
                    .replace("[TICKER_ID]", ticker.getName());

            try {
                JsonNode jsonNode = JsonUtils.readJsonFromUrl(directUrl);
                Iterator<JsonNode> elements = jsonNode.elements();
                Price price = new Price();

                while (elements.hasNext()) {

                    JsonNode element = elements.next();

                    if (element instanceof ObjectNode) {

                        ObjectNode priceJson = (ObjectNode) element;
                        double prevPrice = priceJson.findValue("PREVPRICE").asDouble();
                        double openPrice =  priceJson.findValue("OPEN").asDouble();
                        double highPrice =  priceJson.findValue("HIGH").asDouble();
                        double lowPrice =  priceJson.findValue("LOW").asDouble();
                        double closePrice = priceJson.findValue("LAST").asDouble();

                        if (openPrice == 0 && highPrice == 0 && lowPrice == 0 && closePrice == 0 && prevPrice != 0) {
                            openPrice = highPrice = lowPrice = closePrice = prevPrice;
                        }

                        price.setOpen((float)openPrice);
                        price.setHigh((float)highPrice);
                        price.setLow((float)lowPrice);
                        price.setClose((float)closePrice);

                        //TODO: Продолжить маппинг
                    }
                }

            } catch (IOException e) {
                //TODO: Добавить логер
                e.printStackTrace();
            }
        }
    }
}
