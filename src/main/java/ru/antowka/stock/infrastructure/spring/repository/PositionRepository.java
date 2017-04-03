package ru.antowka.stock.infrastructure.spring.repository;


import org.springframework.data.jpa.repository.Query;
import ru.antowka.stock.domain.model.portfolio.vo.Position;

import java.util.List;

/**
 * JPA repository for tickers
 */
public interface PositionRepository extends ReadOnlyRepository<Position, String> {

    @Override
    @Query(value = "SELECT " +
            "  t.ticker, " +
            "  SUM(t.amount)  AS amount, " +
            "  round(CAST(SUM(t.sum) AS NUMERIC), 3)     AS sum, " +
            "  round(CAST(SUM(t.sum) / SUM(t.amount) AS NUMERIC), 2)   AS average_price, " +
            "0 AS last_market_place " +
            "FROM ( " +
            "       SELECT " +
            "         tk.name     AS ticker, " +
            "         tbuy.amount AS amount, " +
            "         tbuy.price  AS price, " +
            "         tbuy.amount * tbuy.price AS sum " +
            "       FROM transactions tbuy " +
            "         LEFT JOIN transaction_types tt ON tbuy.type_id = tt.id " +
            "         LEFT JOIN tickers tk ON tbuy.ticker_id = tk.id " +
            "       WHERE tt.name = 'BUY' " +
            "       UNION ALL " +
            "       SELECT " +
            "         tk.name         AS ticker, " +
            "         0 - tbuy.amount AS amount, " +
            "         NULL            AS price, " +
            "         NULL            AS sum " +
            "       FROM transactions tbuy " +
            "         LEFT JOIN transaction_types tt ON tbuy.type_id = tt.id " +
            "         LEFT JOIN tickers tk ON tbuy.ticker_id = tk.id " +
            "       WHERE tt.name = 'SELL' " +
            "     ) t " +
            "GROUP BY t.ticker", nativeQuery = true)
    List<Position> findAll();
}
