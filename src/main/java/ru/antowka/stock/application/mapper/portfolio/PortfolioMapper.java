package ru.antowka.stock.application.mapper.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.antowka.stock.application.mapper.Mapper;
import ru.antowka.stock.application.mapper.position.PositionMapper;
import ru.antowka.stock.application.representation.portfolio.PortfolioRepresentation;
import ru.antowka.stock.application.representation.position.PositionRepresentation;
import ru.antowka.stock.domain.model.portfolio.Portfolio;
import ru.antowka.stock.domain.model.portfolio.vo.Position;

import java.util.stream.Collectors;

/**
 * Mapper for position VIEW
 */
@Component
public class PortfolioMapper implements Mapper<Portfolio, PortfolioRepresentation> {

    private PositionMapper positionMapper;

    @Autowired
    public void setPositionMapper(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }

    @Override
    public PortfolioRepresentation toRepresentation(Portfolio entity) {

        PortfolioRepresentation representation = new PortfolioRepresentation();
        representation.setPositions(entity
                .getPositions()
                .stream()
                .map(position -> positionMapper.toRepresentation(position))
                .collect(Collectors.toList())
        );

        representation.setInvested(entity.getInvested());
        representation.setLiquidationValue(entity.getLiquidationValue());

        return representation;
    }

    @Override
    public Portfolio toEntity(PortfolioRepresentation command) {

//        Portfolio portfolio = new Portfolio();
//        portfolio.setPositions(command
//                .getPositions()
//                .stream()
//                .map(positionRepresentation -> positionMapper.toEntity(positionRepresentation))
//                .collect(Collectors.toList())
//        );
//
//        return portfolio;
        return null;
    }
}
