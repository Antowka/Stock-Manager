package ru.antowka.stock.application.representation;

import lombok.Data;

import java.util.Map;

/**
 * Status action - response SUCCESS / FAIL for any action
 */
@Data
public class StatusAction {

    private boolean success;
    private String message;
    private Map<String, String> additionProperties;
}
