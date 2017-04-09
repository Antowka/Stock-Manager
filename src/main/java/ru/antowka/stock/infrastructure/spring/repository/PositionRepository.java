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
            "  t.ticker_id, " +
            "  t.ticker_name, " +
            "  SUM(t.amount)                                         AS amount, " +
            "  round(CAST(SUM(t.sum) AS NUMERIC), 3)                 AS sum, " +
            "  round(CAST(SUM(t.sum) / SUM(t.amount) AS NUMERIC), 2) AS average_price, " +
            "  MAX(p.prev_price)                                     AS last_market_place " +
            "FROM ( " +
            "       SELECT " +
            "         tk.id                    AS ticker_id, " +
            "         tk.name                  AS ticker_name, " +
            "         tbuy.amount              AS amount, " +
            "         tbuy.price               AS price, " +
            "         tbuy.amount * tbuy.price AS sum " +
            "       FROM transactions tbuy " +
            "         LEFT JOIN transaction_types tt ON tbuy.type_id = tt.id " +
            "         LEFT JOIN tickers tk ON tbuy.ticker_id = tk.id " +
            "       WHERE tt.name = 'BUY' " +
            "       UNION ALL " +
            "       SELECT " +
            "         tk.id           AS tiker_id, " +
            "         tk.name         AS ticker_name, " +
            "         0 - tbuy.amount AS amount, " +
            "         NULL            AS price, " +
            "         NULL            AS sum " +
            "       FROM transactions tbuy " +
            "         LEFT JOIN transaction_types tt ON tbuy.type_id = tt.id " +
            "         LEFT JOIN tickers tk ON tbuy.ticker_id = tk.id " +
            "       WHERE tt.name = 'SELL' " +
            "     ) t " +
            "  LEFT JOIN price p ON p.ticker_id = t.ticker_id AND p.id = (SELECT id FROM price WHERE ticker_id = t.ticker_id ORDER BY date DESC LIMIT 1) " +
            "GROUP BY t.ticker_id, t.ticker_name", nativeQuery = true)
    List<Position> findAll();
}
