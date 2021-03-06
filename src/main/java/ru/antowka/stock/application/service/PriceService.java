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
import ru.antowka.stock.infrastructure.utils.DateUtils;
import ru.antowka.stock.infrastructure.utils.JsonUtils;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Update price
 */
@Service
public class PriceService {

    private PriceRepository priceRepository;
    private TickerRepository tickerRepository;
    private PortfolioService portfolioService;

    private static final String url = "http://www.micex.ru/issrpc/marketdata/stock/shares/daily/short/result.json?boardid=[BOARD_ID]&secid=[TICKER_ID]";

    @Autowired
    public void setPriceRepository(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Autowired
    public void setTickerRepository(TickerRepository tickerRepository) {
        this.tickerRepository = tickerRepository;
    }

    @Autowired
    public void setPortfolioService(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @Scheduled(cron="*/25 * 0-10 * * MON-FRI")
    public void loadPrices() {

        Ticker ticker = tickerRepository.findByMaxOldUpdateDate();

        String directUrl = url
                .replace("[BOARD_ID]", ticker.getBoardId())
                .replace("[TICKER_ID]", ticker.getName());

        try {

            JsonNode jsonNode = JsonUtils.readJsonFromUrl(directUrl);

            if (jsonNode == null) {
                System.out.println("Can't read: " + directUrl);

                final Ticker savedTicker = tickerRepository.save(ticker);
                savedTicker.setLastUpdatePrice(new Date());
                tickerRepository.saveAndFlush(savedTicker);

                return;
            }

            Iterator<JsonNode> elements = jsonNode.elements();
            Price price = new Price();

            while (elements.hasNext()) {

                JsonNode element = elements.next();

                if (element instanceof ObjectNode) {

                    ObjectNode priceJson = (ObjectNode) element;
                    double prevPrice = priceJson.findValue("PREVPRICE").asDouble();

                    double openPrice =  priceJson.findValue("OPEN").asDouble();
                    openPrice = openPrice > 0 ? openPrice : prevPrice;

                    double highPrice =  priceJson.findValue("HIGH").asDouble();
                    highPrice = highPrice > 0 ? highPrice : prevPrice;

                    double lowPrice =  priceJson.findValue("LOW").asDouble();
                    lowPrice = lowPrice > 0 ? lowPrice : prevPrice;

                    double closePrice = priceJson.findValue("LAST").asDouble();
                    closePrice = closePrice > 0 ? closePrice : prevPrice;

                    price.setOpen((float)openPrice);
                    price.setHigh((float)highPrice);
                    price.setLow((float)lowPrice);
                    price.setClose((float)closePrice);
                    price.setTicker(ticker);
                    price.setDate(DateUtils.stringToDate(priceJson.findValue("SYSTIME").asText()));
                    price.setVolume(priceJson.findValue("VALTODAY").asLong());
                    price.setPrevPrice((float)prevPrice);

                    final Price savedPrice = priceRepository.saveAndFlush(price);

                    ticker.setLastPrice(savedPrice);
                    ticker.setLastUpdatePrice(new Date());
                    final Ticker savedTicker = tickerRepository.save(ticker);
                    portfolioService.updatePrice(savedTicker);

                    break;
                }
            }

        } catch (IOException e) {
            //TODO: Добавить логер
            e.printStackTrace();
        }
    }
}
