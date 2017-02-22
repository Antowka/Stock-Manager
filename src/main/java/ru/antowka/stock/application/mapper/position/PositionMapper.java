package ru.antowka.stock.application.mapper.position;

import org.springframework.stereotype.Component;
import ru.antowka.stock.application.mapper.Mapper;
import ru.antowka.stock.application.representation.position.PositionRepresentation;
import ru.antowka.stock.domain.model.portfolio.vo.Position;

/**
 * Mapper for position VIEW
 */
@Component
public class PositionMapper implements Mapper<Position, PositionRepresentation> {


    @Override
    public PositionRepresentation toRepresentation(Position entity) {

        PositionRepresentation representation = new PositionRepresentation();
        representation.setTicker(entity.getTicker());
        representation.setSum(entity.getSum());
        representation.setAveragePrice(entity.getAveragePrice());
        representation.setAmount(entity.getAmount());

        return representation;
    }

    @Override
    public Position toEntity(PositionRepresentation command) {

        Position position = new Position();
        position.setTicker(command.getTicker());
        position.setSum(command.getSum());
        position.setAveragePrice(command.getAveragePrice());
        position.setAmount(command.getAmount());

        return position;
    }
}
