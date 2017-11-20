package ru.antowka.stock.infrastructure.vaadin.portfolio;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ru.antowka.stock.application.mapper.position.PositionMapper;
import ru.antowka.stock.application.representation.position.PositionRepresentation;
import ru.antowka.stock.application.representation.transaction.TransactionRepresentation;
import ru.antowka.stock.application.service.PortfolioService;
import ru.antowka.stock.domain.model.portfolio.Portfolio;
import ru.antowka.stock.domain.model.portfolio.vo.Position;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Portfolio view
 */
@SpringView(name = PortfolioView.VIEW_NAME)
public class PortfolioView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "portfolio";

    private Grid positionsGrid = new Grid();
    private final TextField filterByTicker = new TextField();
    private PortfolioService portfolioService;
    private PositionMapper positionMapper;

    @Autowired
    public void setPortfolioService(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @Autowired
    public void setPositionMapper(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }


    @PostConstruct
    void init() {

        // build layout
        VerticalLayout mainLayout = new VerticalLayout();
        HorizontalLayout actions = new HorizontalLayout(filterByTicker);
        mainLayout.addComponent(actions);
        mainLayout.addComponent(positionsGrid);
        addComponent(mainLayout);

        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        filterByTicker.setInputPrompt("Filter by ticker");

        positionsGrid.setWidth(100, Unit.PERCENTAGE);
        positionsGrid.setColumns(
                "ticker",
                "amount",
                "averagePrice",
                "lastMarketPlace",
                "sum",
                "diffPricesPercent",
                "averageProfit");

        final Portfolio portfolio = portfolioService.getPortfolio();

        List<PositionRepresentation> positions = portfolio
                .getPositions()
                .stream()
                .map(position -> positionMapper.toRepresentation(position))
                .collect(Collectors.toList());

        // Replace listing with filtered content when user changes filter
        filterByTicker.addTextChangeListener(e -> positionList(e.getText(), positions));


        this.positionsGrid.setContainerDataSource(new BeanItemContainer<>(PositionRepresentation.class, positions));

        Label invested = new Label("Invested: " + portfolio.getInvested());
        Label liquidationValue = new Label("Liquidation Value: " + portfolio.getLiquidationValue());
        Label profit = new Label("Profit: " + (portfolio.getLiquidationValue() - portfolio.getInvested()));

        mainLayout.addComponent(invested);
        mainLayout.addComponent(liquidationValue);
        mainLayout.addComponent(profit);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    @SuppressWarnings("unchecked")
    private void positionList(String ticker, List<PositionRepresentation> positions) {

        if (StringUtils.isEmpty(ticker)) {

            positionsGrid.setContainerDataSource(
                    new BeanItemContainer(PositionRepresentation.class, positions));

        } else {

            final List<PositionRepresentation> positionsFiltered = positions
                    .stream()
                    .filter(position -> position.getTicker().toLowerCase().equals(ticker.toLowerCase()))
                    .collect(Collectors.toList());

            positionsGrid.setContainerDataSource(
                    new BeanItemContainer(PositionRepresentation.class, positionsFiltered));
        }
    }
}
