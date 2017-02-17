package ru.antowka.stock.infrastructure.utils;

import org.apache.log4j.Logger;
import ru.antowka.stock.application.service.TransactionService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date converter
 */
public class DateUtils {

    private static final String DATE_PATERN = "yyyy-mm-dd HH:mm:ss";
    private final static Logger logger = Logger.getLogger(DateUtils.class);

    /**
     * Convert Date to String
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_PATERN);
        return df.format(date);
    }

    /**
     * Convert String to Date
     *
     * @param dateString
     * @return
     */
    public static Date stringToDate(String dateString) {

        DateFormat df = new SimpleDateFormat(DATE_PATERN);

        try {
            return df.parse(dateString);
        } catch (NullPointerException | ParseException e) {
            logger.error("Wrong date format: " + dateString);
            return new Date();
        }
    }
}
