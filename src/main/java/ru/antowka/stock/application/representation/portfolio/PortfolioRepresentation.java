package ru.antowka.stock.application.representation.portfolio;

import lombok.Data;
import ru.antowka.stock.application.representation.position.PositionRepresentation;

import java.util.List;

/**
 * Representation for Portfolio
 */
@Data
public class PortfolioRepresentation {

    private List<PositionRepresentation> positions;
}
