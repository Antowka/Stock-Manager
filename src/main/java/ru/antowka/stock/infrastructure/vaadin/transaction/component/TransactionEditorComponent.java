package ru.antowka.stock.infrastructure.vaadin.transaction.component;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import ru.antowka.stock.application.mapper.transaction.TransactionMapper;
import ru.antowka.stock.application.representation.transaction.TransactionRepresentation;
import ru.antowka.stock.application.service.PortfolioService;
import ru.antowka.stock.application.service.TickerService;
import ru.antowka.stock.application.service.TransactionService;
import ru.antowka.stock.domain.model.ticker.Ticker;
import ru.antowka.stock.domain.model.transaction.Transaction;
import ru.antowka.stock.domain.model.transaction.TransactionType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Component Editor for transaction
 *
 * TODO: Example: https://spring.io/guides/gs/crud-with-vaadin/
 */
@UIScope
@SpringComponent
public class TransactionEditorComponent extends VerticalLayout {

    private TransactionService transactionService;

    private TickerService tickerService;

    private PortfolioService portfolioService;

    private TransactionMapper transactionMapper;

    private TransactionRepresentation currentEditionTransaction;

    /* Fields to edit properties in Customer entity */
    private TextField id = new TextField("id");
    private ListSelect type = new ListSelect("type");
    private ListSelect ticker = new ListSelect("ticker");
    private TextField price = new TextField("price");
    private TextField amount = new TextField("amount");
    private TextArea comment = new TextArea("comment");
    private DateField date = new DateField("date");

    /* Action buttons */
    private Button save = new Button("Save", FontAwesome.SAVE);
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete", FontAwesome.TRASH_O);
    private CssLayout actions = new CssLayout(save, cancel, delete);


    @Autowired
    public TransactionEditorComponent(
            TransactionService transactionService,
            TransactionMapper transactionMapper, TickerService tickerService, PortfolioService portfolioService) {

        this.transactionService = transactionService;
        this.tickerService = tickerService;
        this.portfolioService = portfolioService;
        this.transactionMapper = transactionMapper;

        configComponentElements();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public void editTransaction(TransactionRepresentation currentTransaction) {

        final boolean persisted = currentTransaction.getId() != null;

        if (persisted) {

            Transaction transaction = transactionService.getById(currentTransaction.getId());
            currentEditionTransaction = transactionMapper.toRepresentation(transaction);
        } else {

            currentEditionTransaction = currentTransaction;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        BeanFieldGroup.bindFieldsUnbuffered(currentEditionTransaction, this);

        setVisible(true);

        // A hack to ensure the whole form is visible
        save.focus();

        id.selectAll();
        type.addItems(transactionService.getTransactionTypesList().stream().map(TransactionType::getName).collect(Collectors.toList()));
        ticker.addItems(tickerService.getTickersList().stream().map(Ticker::getName).collect(Collectors.toList()));
        price.selectAll();
        amount.selectAll();
        comment.selectAll();
        date.setValue(currentEditionTransaction.getDate());
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }

    private void configComponentElements() {

        HorizontalLayout hlTypeTicker = new HorizontalLayout(type, ticker, date);
        HorizontalLayout hlPricaAmount = new HorizontalLayout(price, amount);
        addComponents(hlTypeTicker, hlPricaAmount, comment, actions);

        hlTypeTicker.setSpacing(true);
        hlPricaAmount.setSpacing(true);

        type.setRows(1);
        ticker.setRows(1);
        ticker.setWidth(105, Unit.PIXELS);

        comment.setWidth(380, Unit.PIXELS);

        // Configure and style components
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> {
            if (currentEditionTransaction.getId() != null) {
                updateTransaction(currentEditionTransaction);
            } else {
                createTransaction(currentEditionTransaction);
            }
        });

        delete.addClickListener(e -> deleteTransaction());
        cancel.addClickListener(e -> setVisible(false));

        setVisible(false);
    }

    private void deleteTransaction() {
        Transaction transaction = transactionMapper.toEntity(currentEditionTransaction);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        transactionService.delete(transactions);
    }

    private void updateTransaction(TransactionRepresentation transactionRepresentation) {
        Transaction transaction = transactionMapper.toEntity(transactionRepresentation);
        final Transaction savedTransaction = transactionService.update(transaction);
        portfolioService.updatePosition(savedTransaction);
    }

    private void createTransaction(TransactionRepresentation transactionRepresentation) {
        Transaction transaction = transactionMapper.toEntity(transactionRepresentation);
        final Transaction savedTransaction = transactionService.create(transaction);
        portfolioService.updatePosition(savedTransaction);
    }
}
