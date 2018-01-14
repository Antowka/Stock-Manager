package ru.antowka.stock.infrastructure.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.antowka.stock.domain.model.portfolio.vo.Position;
import ru.antowka.stock.domain.model.ticker.Ticker;

import java.util.List;

/**
 * JPA repository for tickers
 */
public interface PositionRepository extends JpaRepository<Position, Long> {

    Position findOneByTicker(Ticker ticker);
}
