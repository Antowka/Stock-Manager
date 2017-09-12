package ru.antowka.stock.domain.model.portfolio;

import lombok.Data;
import ru.antowka.stock.domain.model.portfolio.vo.Position;

import javax.persistence.Transient;
import java.util.List;

/**
 * Representation for portfolio
 */
@Data
public class Portfolio {

    private List<Position> positions;

    private float invested;

    private float liquidationValue;
}
