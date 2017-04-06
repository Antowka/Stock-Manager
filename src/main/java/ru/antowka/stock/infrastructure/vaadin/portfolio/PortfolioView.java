package ru.antowka.stock.infrastructure.vaadin.portfolio;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import ru.antowka.stock.application.mapper.position.PositionMapper;
import ru.antowka.stock.application.representation.position.PositionRepresentation;
import ru.antowka.stock.application.service.PortfolioService;
import ru.antowka.stock.infrastructure.vaadin.partial.HeaderMenuLayout;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Portfolio view
 */
@SpringView(name = PortfolioView.VIEW_NAME)
public class PortfolioView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "portfolio";

    private Grid grid = new Grid();
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
        mainLayout.addComponent(grid);
        addComponent(mainLayout);

        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        grid.setWidth(100, Unit.PERCENTAGE);
        grid.setColumns("ticker", "amount", "averagePrice", "lastMarketPlace", "sum");

        List<PositionRepresentation> positions = portfolioService
                .getPortfolio()
                .getPositions()
                .stream()
                .map(position -> positionMapper.toRepresentation(position))
                .collect(Collectors.toList());


        grid.setContainerDataSource(new BeanItemContainer<>(PositionRepresentation.class, positions));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
