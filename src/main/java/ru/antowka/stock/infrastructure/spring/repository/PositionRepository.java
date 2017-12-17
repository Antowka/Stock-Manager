package ru.antowka.stock.infrastructure.spring.repository;


import org.springframework.data.jpa.repository.Query;
import ru.antowka.stock.domain.model.portfolio.vo.Position;

import java.util.List;

/**
 * JPA repository for tickers
 */
public interface PositionRepository extends ReadOnlyRepository<Position, String> {

    @Override
    @Query(value = "SELECT * FROM (" +
            "SELECT " +
            "  t.ticker_id, " +
            "  t.ticker_name, " +
            "  SUM(t.amount)                                         AS amount, " +
            "  round(CAST(SUM(t.sum) AS NUMERIC), 3)                 AS sum, " +
            "  round(CAST(SUM(t.sum) / NULLIF(SUM(t.amount),0) AS NUMERIC), 2) AS average_price, " +
            "  MAX(p.prev_price)                                     AS last_market_place, " +
            "  round(CAST((MAX(p.prev_price) / (SUM(t.sum) / NULLIF(SUM(t.amount),0)) - 1)*100 AS NUMERIC),2) AS diff_prices_percent, " +
            "  round(CAST((MAX(p.prev_price)  * NULLIF(SUM(t.amount),0)) - SUM(t.sum) AS NUMERIC),2)  AS average_profit " +
            "FROM ( " +
            "       SELECT " +
            "            tk.id                    AS ticker_id, " +
            "            tk.name                  AS ticker_name, " +
            "            tbuy.price               AS price, " +
            "            CASE " +
            "              WHEN tt.name = 'SELL' THEN 0 - tbuy.amount " +
            "              WHEN tt.name = 'BUY' THEN tbuy.amount " +
            "            END AS amount, " +
            "            CASE " +
            "               WHEN tt.name = 'BUY' THEN tbuy.amount * tbuy.price " +
            "               WHEN tt.name = 'SELL' THEN  (0 - tbuy.amount) * tbuy.price " +
            "            END AS sum " +
            "            FROM transactions tbuy " +
            "                LEFT JOIN transaction_types tt ON tbuy.type_id = tt.id " +
            "                LEFT JOIN tickers tk ON tbuy.ticker_id = tk.id " +
            "            ORDER BY date ASC     ) t " +
            "  LEFT JOIN price p ON p.ticker_id = t.ticker_id AND p.id = (SELECT id FROM price WHERE ticker_id = t.ticker_id ORDER BY date DESC LIMIT 1) " +
            "GROUP BY t.ticker_id, t.ticker_name" +
            ") r WHERE r.amount > 0", nativeQuery = true)
    List<Position> findAll();
}
