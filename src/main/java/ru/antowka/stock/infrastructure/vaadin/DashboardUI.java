package ru.antowka.stock.infrastructure.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.antowka.stock.infrastructure.vaadin.partial.HeaderMenuLayout;

/**
 * Start UI
 */
@SpringUI
@Theme("valo")
public class DashboardUI extends UI {

    private SpringViewProvider viewProvider;
    private HeaderMenuLayout headerMenuLayout;

    @Autowired
    public DashboardUI(SpringNavigator navigator, SpringViewProvider viewProvider, HeaderMenuLayout headerMenuLayout) {
        this.viewProvider = viewProvider;
        this.headerMenuLayout = headerMenuLayout;

        navigator.init(this, this);
        navigator.addProvider(viewProvider);
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

    }
}
