package ru.antowka.stock.domain.model.portfolio;

import lombok.Data;
import ru.antowka.stock.domain.model.portfolio.vo.Position;

import java.util.List;

/**
 * Representation for portfolio
 */
@Data
public class Portfolio {

    private List<Position> positions;

}
