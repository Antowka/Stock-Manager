package ru.antowka.stock.infrastructure.vaadin.partial;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by anton on 01.04.17.
 */
@UIScope
@SpringComponent
public class HeaderMenuLayout extends VerticalLayout {

    private SpringNavigator navigator;

    @Autowired
    public void setNavigator(SpringNavigator navigator) {
        this.navigator = navigator;
    }

    @PostConstruct
    private void init() {
        HorizontalLayout hl = new HorizontalLayout();
        hl.setDefaultComponentAlignment(Alignment.BOTTOM_CENTER);
        hl.setWidth(100, Unit.PERCENTAGE);

        MenuBar barmenu = new MenuBar();
        barmenu.addStyleName("mybarmenu");
        hl.addComponent(barmenu);


        // Define a common menu command for all the menu items
        MenuBar.Command mycommand = new MenuBar.Command() {
            MenuBar.MenuItem previous = null;

            public void menuSelected(MenuBar.MenuItem selectedItem) {

                navigator.navigateTo(selectedItem.getText().toLowerCase());

                if (previous != null) {
                    previous.setStyleName(null);
                }

                selectedItem.setStyleName("highlight");
                previous = selectedItem;
            }
        };

        barmenu.addItem("", null, mycommand);
        barmenu.addItem("Transaction", null, mycommand);
        barmenu.addItem("Portfolio", null, mycommand);

        addComponent(hl);
    }
}
