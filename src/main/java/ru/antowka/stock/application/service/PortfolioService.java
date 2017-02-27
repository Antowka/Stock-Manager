package ru.antowka.stock.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.stock.domain.model.portfolio.Portfolio;
import ru.antowka.stock.infrastructure.spring.repository.PositionRepository;

/**
 * Portfolio service
 */
@Service
public class PortfolioService {

    private PositionRepository positionRepository;

    @Autowired
    public void setPositionRepository(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    /**
     * Get portfolio
     *
     * @return
     */
    public Portfolio getPortfolio() {
        Portfolio portfolio = new Portfolio();
        portfolio.setPositions(positionRepository.findAll());
        return portfolio;
    }
}