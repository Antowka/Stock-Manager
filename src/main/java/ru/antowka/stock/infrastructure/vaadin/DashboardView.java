package ru.antowka.stock.infrastructure.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import ru.antowka.stock.infrastructure.vaadin.partial.HeaderMenuLayout;


/**
 * Main VIEW
 */
@SpringView(name = DashboardView.VIEW_NAME)
public class DashboardView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "";

    private SpringNavigator navigator;

    private HeaderMenuLayout headerMenuLayout;

    @Autowired
    public void setNavigator(SpringNavigator navigator) {
        this.navigator = navigator;
    }

    @Autowired
    public void setHeaderMenuLayout(HeaderMenuLayout headerMenuLayout) {
        this.headerMenuLayout = headerMenuLayout;
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        addComponent(headerMenuLayout);
    }
}
