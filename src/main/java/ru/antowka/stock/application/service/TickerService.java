package ru.antowka.stock.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.stock.domain.model.ticker.Ticker;
import ru.antowka.stock.infrastructure.spring.repository.TickerRepository;

import java.util.List;

/**
 * Created by anton on 19.02.17.
 */
@Service
public class TickerService {

    private TickerRepository tickerRepository;

    @Autowired
    public void setTickerRepository(TickerRepository tickerRepository) {
        this.tickerRepository = tickerRepository;
    }

    /**
     * Response ticker list
     *
     * @return
     */
    public List<Ticker> getTickersList() {
        return tickerRepository.findAll();
    }
}
