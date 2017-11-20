package ru.antowka.stock.infrastructure.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import ru.antowka.stock.infrastructure.vaadin.partial.HeaderMenuLayout;

/**
 * Start UI
 */
@SpringUI
@Theme("valo")
@SpringViewDisplay
public class DashboardUI extends UI implements ViewDisplay {

    private HeaderMenuLayout headerMenuLayout;
    private Panel springViewDisplay;

    @Autowired
    public DashboardUI(HeaderMenuLayout headerMenuLayout) {
        this.headerMenuLayout = headerMenuLayout;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.addComponents(headerMenuLayout);
        setContent(layout);

        springViewDisplay = new Panel();
        springViewDisplay.setSizeFull();
        layout.addComponent(springViewDisplay);
        layout.setExpandRatio(springViewDisplay, 1.0f);
    }


    @Override
    public void showView(View view) {
        springViewDisplay.setContent((Component) view);
    }
}
