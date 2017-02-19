package ru.antowka.stock.infrastructure.vaadin.transaction;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ru.antowka.stock.application.mapper.transaction.TransactionMapper;
import ru.antowka.stock.application.representation.transaction.TransactionRepresentation;
import ru.antowka.stock.application.service.TransactionService;
import ru.antowka.stock.infrastructure.vaadin.transaction.component.TransactionEditorComponent;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by anton on 19.02.17.
 */
@SpringUI
@Theme("valo")
public class TransactionGridUI extends UI {

    private Grid grid;
    private TransactionService transactionService;
    private TransactionMapper transactionMapper;
    private TransactionEditorComponent transactionEditor;
    private final TextField filterByTicker;
    private final Button addNewBtn;

    @Autowired
    public TransactionGridUI(
            TransactionService transactionService,
            TransactionEditorComponent transactionEditor,
            TransactionMapper transactionMapper) {

        this.grid = new Grid();
        this.transactionMapper = transactionMapper;
        this.transactionService = transactionService;
        this.transactionEditor = transactionEditor;
        this.filterByTicker = new TextField();
        this.addNewBtn = new Button("Add transaction", FontAwesome.PLUS);
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        // build layout
        HorizontalLayout actions = new HorizontalLayout(filterByTicker, addNewBtn);
        HorizontalLayout gridAndEditor = new HorizontalLayout(grid, transactionEditor);
        VerticalLayout mainLayout = new VerticalLayout(actions, gridAndEditor);
        setContent(mainLayout);

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
