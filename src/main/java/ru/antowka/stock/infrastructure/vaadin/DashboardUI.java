package ru.antowka.stock.infrastructure.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ru.antowka.stock.infrastructure.vaadin.partial.HeaderMenuLayout;

/**
 * Start UI
 */
@SpringUI
@Theme("antowka")
public class DashboardUI extends UI {

    private SpringViewProvider viewProvider;
    private HeaderMenuLayout headerMenuLayout;
    private ApplicationContext appContext;

    @Autowired
    public DashboardUI(SpringNavigator navigator, SpringViewProvider viewProvider, HeaderMenuLayout headerMenuLayout, ApplicationContext appContext) {
        this.viewProvider = viewProvider;
        this.headerMenuLayout = headerMenuLayout;
        this.appContext = appContext;

        navigator.init(this, this);
        navigator.addProvider(viewProvider);
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        CustomLayout layout = new CustomLayout("main-layout.html");

        Label header = new Label("Custom layout");
        header.addStyleName("header");
        layout.addComponent(header, "header");
        layout.addComponent(headerMenuLayout, "header");

        Label menu = new Label("menu");
        layout.addComponent(menu, "menu");

        Label content = new Label("This is content of page.");
        layout.addComponent(content, "content");

        Label footer = new Label("Created by Vaadin, 2013");
        layout.addComponent(footer, "footer");

        setContent(layout);
    }

    /**
     * Returns current application context
     *
     * @return the application context
     */
    public final ApplicationContext getAppContext() {
        return appContext;
    }
}
