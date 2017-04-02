package ru.antowka.stock.infrastructure.vaadin.transaction;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.util.StringUtils;
import ru.antowka.stock.application.mapper.transaction.TransactionMapper;
import ru.antowka.stock.application.representation.transaction.TransactionRepresentation;
import ru.antowka.stock.application.service.TransactionService;
import ru.antowka.stock.infrastructure.vaadin.partial.HeaderMenuLayout;
import ru.antowka.stock.infrastructure.vaadin.transaction.component.TransactionEditorComponent;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Portfolio view
 */
@SpringView(name = TransactionView.VIEW_NAME)
public class TransactionView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "transaction";

    private Grid grid = new Grid();
    private TransactionService transactionService;
    private TransactionMapper transactionMapper;
    private TransactionEditorComponent transactionEditor;
    private HeaderMenuLayout headerMenuLayout;
    private final TextField filterByTicker = new TextField();
    private final Button addNewBtn = new Button("Add transaction", FontAwesome.PLUS);;


    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setTransactionMapper(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    @Autowired
    public void setTransactionEditor(TransactionEditorComponent transactionEditor) {
        this.transactionEditor = transactionEditor;
    }

    @Autowired
    public void setHeaderMenuLayout(HeaderMenuLayout headerMenuLayout) {
        this.headerMenuLayout = headerMenuLayout;
    }

    @PostConstruct
    void init() {

        // build layout
        HorizontalLayout actions = new HorizontalLayout(filterByTicker, addNewBtn);
        HorizontalLayout gridAndEditor = new HorizontalLayout(grid, transactionEditor);
        VerticalLayout mainLayout = new VerticalLayout(headerMenuLayout, actions, gridAndEditor);
        addComponent(mainLayout);

        // Configure layouts and components
        actions.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        gridAndEditor.setWidth(100, Unit.PERCENTAGE);
        gridAndEditor.setSpacing(true);

        grid.setWidth(100, Unit.PERCENTAGE);
        grid.setColumns("id", "type", "ticker", "price", "amount", "comment", "date");

        filterByTicker.setInputPrompt("Filter by ticker");

        // Replace listing with filtered content when user changes filter
        filterByTicker.addTextChangeListener(e -> transactionsList(e.getText()));

        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                transactionEditor.setVisible(false);
            } else {
                transactionEditor.editTransaction((TransactionRepresentation) grid.getSelectedRow());
            }
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn
                .addClickListener(e -> {

                    TransactionRepresentation transactionRepresentation = new TransactionRepresentation();
                    transactionRepresentation.setAmount(0);
                    transactionRepresentation.setPrice(0f);
                    transactionRepresentation.setComment("");
                    transactionRepresentation.setDate(new Date());

                    transactionEditor.editTransaction(transactionRepresentation);
                });

        // Listen changes made by the editor, refresh data from backend
        transactionEditor.setChangeHandler(() -> {
            transactionEditor.setVisible(false);
            transactionsList(filterByTicker.getValue());
        });

        // Initialize listing
        transactionsList(null);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    @SuppressWarnings("unchecked")
    private void transactionsList(String ticker) {

        List<TransactionRepresentation> transactionRepresentationList = null;

        if (StringUtils.isEmpty(ticker)) {

            transactionRepresentationList = transactionService
                    .getAllTransaction()
                    .stream()
                    .map(transaction -> transactionMapper.toRepresentation(transaction))
                    .collect(Collectors.toList());
        } else {

            transactionRepresentationList = transactionService
                    .getByTickerName(ticker)
                    .stream()
                    .map(transaction -> transactionMapper.toRepresentation(transaction))
                    .collect(Collectors.toList());
        }

        grid.setContainerDataSource(
                new BeanItemContainer(TransactionRepresentation.class, transactionRepresentationList));
    }
}
