package ru.antowka.stock.infrastructure.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.layouts.MVerticalLayout;
import ru.antowka.stock.infrastructure.vaadin.portfolio.PortfolioView;
import ru.antowka.stock.infrastructure.vaadin.transaction.TransactionView;

import javax.annotation.PostConstruct;


/**
 * Start view
 */
@SpringView(name = DashboardView.VIEW_NAME)
public class DashboardView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "";

    private SpringNavigator navigator;

    @Autowired
    public void setNavigator(SpringNavigator navigator) {
        this.navigator = navigator;
    }

    @PostConstruct
    void init() {
        addComponent(createNavigationBar());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    private Component createNavigationBar() {
        MVerticalLayout m = new MVerticalLayout().withWidth("300px");
        m.addComponent(createNavButton("Portfolio", PortfolioView.VIEW_NAME));
        m.addComponent(createNavButton("Transaction", TransactionView.VIEW_NAME));
        return m;
    }

    private Component createNavButton(String first, String viewName) {
        Button button = new Button(first);
        button.addClickListener(e->navigator.navigateTo(viewName));
        button.addStyleName(ValoTheme.BUTTON_LARGE);
        button.addStyleName(ValoTheme.BUTTON_LINK);
        return button;
    }
}
