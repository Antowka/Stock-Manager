package ru.antowka.stock.infrastructure.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.antowka.stock.domain.model.ticker.Ticker;

/**
 * JPA repository for tickers
 */
public interface TickerRepository extends JpaRepository<Ticker, Long> {
    Ticker findByName(String name);


    @Query(value = "SELECT * FROM tickers ORDER BY last_update_price ASC LIMIT 1", nativeQuery = true)
    Ticker findByMaxOldUpdateDate();
}
