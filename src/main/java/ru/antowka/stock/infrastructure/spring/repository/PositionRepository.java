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
            "  SUM(t.amount) as amount, " +
            "  round(CAST(AVG(t.price) as numeric), 3) as average_price, " +
            "  round(CAST((SUM(t.amount) * AVG(t.price)) as numeric), 2) as sum " +
            "FROM (" +
            "SELECT " +
            "    tk.name     AS ticker, " +
            "    tbuy.amount AS amount, " +
            "    tbuy.price  AS price " +
            "  FROM transactions tbuy " +
            "    LEFT JOIN transaction_types tt ON tbuy.type_id = tt.id " +
            "    LEFT JOIN tickers tk ON tbuy.ticker_id = tk.id " +
            "  WHERE tt.name = 'BUY' " +
            "  UNION ALL " +
            "  SELECT " +
            "    tk.name     AS ticker, " +
            "    0 - tbuy.amount AS amount, " +
            "    null  AS price " +
            "  FROM transactions tbuy " +
            "    LEFT JOIN transaction_types tt ON tbuy.type_id = tt.id " +
            "    LEFT JOIN tickers tk ON tbuy.ticker_id = tk.id " +
            "  WHERE tt.name = 'SELL' " +
            ") t GROUP BY t.ticker", nativeQuery = true)
    List<Position> findAll();
}
