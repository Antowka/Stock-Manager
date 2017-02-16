package ru.antowka.stock.infrastructure.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.antowka.stock.domain.model.ticker.Ticker;

/**
 * JPA repository for tickers
 */
public interface TickerRepository extends JpaRepository<Ticker, Long> {
    Ticker findOneByName(String name);
}
