package ru.antowka.stock.model.factory;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import ru.antowka.stock.model.Price;

/**
 * Created by Anton Nik on 11.11.15.
 */
@Component
public class MyBeanFactory {

    @Lookup
    public Price getNewPrice(){
        return null;
    }
}
