package ru.antowka.stock.service;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import ru.antowka.stock.dao.TickerDao;
import ru.antowka.stock.model.Price;
import ru.antowka.stock.model.Ticker;
import ru.antowka.stock.model.TickerType;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by anton on 15.11.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class TickerServiceTest {

    @InjectMocks
    private TickerService tickerService;

    @Mock
    private TickerDao tickerDao;

    @Before
    public void setupMock() {

    }

    @Test
    public void testUpdateTickerPrices() throws Exception {

        LocalDateTime minusTimeNow = LocalDateTime.now().minusDays(7);

        TickerType tickerType = new TickerType();
        tickerType.setTickerTypeId(1);
        tickerType.setTickertypeName("shares");

        Ticker ticker = new Ticker();
        ticker.setTickerId(1);
        ticker.setBoardId("TQBR");
        ticker.setTickerName("GAZP");
        ticker.setTickerTypeId(tickerType);

        when(tickerDao.getTickerById(1)).thenReturn(ticker);

        Price price = new Price();
        price.setHigh(1.0);
        price.setLast(1.0);
        price.setLow(1.0);
        price.setOpen(1.0);
        price.setPriceId(1);
        price.setTicker(ticker);
        price.setSystime(minusTimeNow);

        List<Price> prices = new ArrayList<>();
        prices.add(price);
        ticker.setPrice(prices);

        when(tickerDao.getLastPrice(ticker)).thenReturn(ticker);

        tickerService.updateTickerPrices(ticker);

        ArgumentCaptor<Ticker> tickerCapture = ArgumentCaptor.forClass(Ticker.class);
        ArgumentCaptor<LocalDateTime> dateCapture = ArgumentCaptor.forClass(LocalDateTime.class);

        verify(tickerDao, atLeast(4)).parsPricesForTicker(tickerCapture.capture(), dateCapture.capture());

        assertTrue(
                dateCapture.getValue().isAfter(minusTimeNow) &&
                dateCapture.getValue().getDayOfWeek() != DayOfWeek.SUNDAY &&
                dateCapture.getValue().getDayOfWeek() != DayOfWeek.SATURDAY
        );
    }
}