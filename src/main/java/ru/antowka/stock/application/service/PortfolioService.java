package ru.antowka.stock.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.stock.domain.model.portfolio.Portfolio;
import ru.antowka.stock.domain.model.portfolio.vo.Position;
import ru.antowka.stock.domain.model.price.Price;
import ru.antowka.stock.domain.model.ticker.Ticker;
import ru.antowka.stock.domain.model.transaction.Transaction;
import ru.antowka.stock.domain.model.transaction.TransactionType;
import ru.antowka.stock.infrastructure.spring.repository.PositionRepository;

import java.util.Date;

import static ru.antowka.stock.application.service.TransactionService.TRANSACTION_BUY;
import static ru.antowka.stock.application.service.TransactionService.TRANSACTION_SELL;

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

        float liquidationValue = 0f;
        float invested = 0f;

        for (Position position : portfolio.getPositions()) {
            invested += position.getSum();
            final Price lastPrice = position.getTicker().getLastPrice();
            liquidationValue += position.getAmount() * (lastPrice != null ? lastPrice.getClose() : 0);
        }

        portfolio.setInvested(invested);
        portfolio.setLiquidationValue(liquidationValue);

        return portfolio;
    }

    public void updatePosition(Transaction transaction) {

        Position position = positionRepository
                .findOneByTicker(transaction.getTicker());

        if (position == null) {
            position = new Position();
            position.setAmount(0);
            position.setAveragePrice(0f);
            position.setSum(0f);
            position.setAveragePrice(0f);
            position.setDiffPricesPercent(0f);
            position.setTicker(transaction.getTicker());
        }

        int remainder = 0;

        switch (transaction.getType().getName()) {

            case TRANSACTION_SELL:
                remainder = position.getAmount() - transaction.getAmount();
                break;
                
            case TRANSACTION_BUY:
                remainder = position.getAmount() + transaction.getAmount();

                final float newAveragePrice = (position.getAveragePrice() * position.getAmount() +
                        transaction.getAmount() * transaction.getPrice()) / remainder;

                position.setAveragePrice(newAveragePrice);
                break;
        }

        position.setAmount(remainder);

        positionRepository.save(position);
    }

    public void updatePrice(Ticker ticker) {

        final Position position = positionRepository
                .findOneByTicker(ticker);

        final Float closePrice = ticker.getLastPrice().getClose();

        final float currentSum = position.getAmount() * closePrice;
        position.setSum(currentSum);

        final float averageInvestedSum = position.getAveragePrice() * position.getAmount();
        position.setAverageProfit(position.getSum() - averageInvestedSum);

        position.setDiffPricesPercent((position.getSum() / averageInvestedSum - 1) * 100);

        positionRepository.save(position);
    }
}
