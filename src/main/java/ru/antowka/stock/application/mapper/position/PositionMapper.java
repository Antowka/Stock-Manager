package ru.antowka.stock.application.mapper.position;

import org.springframework.stereotype.Component;
import ru.antowka.stock.application.mapper.Mapper;
import ru.antowka.stock.application.representation.position.PositionRepresentation;
import ru.antowka.stock.domain.model.portfolio.vo.Position;
import ru.antowka.stock.domain.model.price.Price;

/**
 * Mapper for position VIEW
 */
@Component
public class PositionMapper implements Mapper<Position, PositionRepresentation> {


    @Override
    public PositionRepresentation toRepresentation(Position entity) {

        PositionRepresentation representation = new PositionRepresentation();
        representation.setTicker(entity.getTicker().getName());
        representation.setSum(entity.getSum());
        representation.setAveragePrice(entity.getAveragePrice());
        representation.setAmount(entity.getAmount());
        representation.setDiffPricesPercent(entity.getDiffPricesPercent());
        representation.setAverageProfit(entity.getAverageProfit());

        final Price lastPrice = entity.getTicker().getLastPrice();
        if (lastPrice != null) {
            representation.setLastMarketPrice(lastPrice.getClose());
        }

        return representation;
    }

    @Override
    public Position toEntity(PositionRepresentation command) {

//        Position position = new Position();
//        position.setTicker(command.getTicker());
//        position.setSum(command.getSum());
//        position.setAveragePrice(command.getAveragePrice());
//        position.setAmount(command.getAmount());
//
//        return position;
        return null;
    }
}
