package ru.antowka.stock.infrastructure.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by anton on 19.02.17.
 */
@SpringUI
@Theme("valo")
public class DashboardUI extends UI {

    private SpringViewProvider viewProvider;

    @Autowired
    public DashboardUI(SpringNavigator navigator, SpringViewProvider viewProvider) {
        navigator.init(this, this);
        navigator.addProvider(viewProvider);
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

    }
}
