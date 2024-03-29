package org.ibanfirst.trade.validator;

import org.ibanfirst.trade.utils.ResourceBundleUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TradeValidatorImpl implements TradeValidator {
    @Override
    public String validateReference(String reference)  {
        if (reference == null || reference.isEmpty()) {
            throw new IllegalArgumentException(buildError("reference"));
        }
        return reference;
    }
    @Override
    public LocalDate validateDate(String date, String field) {
        if (date == null || date.isEmpty() || !date.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new IllegalArgumentException(buildError(field));
        }
        return parseDate(date);
    }

    @Override
    public String validateSymbol(String symbol) {
        if (symbol == null || symbol.length() != 6) {
            throw new IllegalArgumentException(buildError("symbol"));
        }
        return symbol;
    }

    @Override
    public String validateCurrency(String currency)  {
        if (currency == null || currency.length() != 3) {
            throw new IllegalArgumentException(buildError("currency"));
        }
        return currency;
    }

    @Override
    public float validateFloat(String floatStr, String field) {
        float result;
        try {
            result = Float.parseFloat(floatStr);
        } catch (NumberFormatException ex){
            throw new IllegalArgumentException(buildError(field));
        }
        if (result <= 0) {
            throw new IllegalArgumentException(buildError(field));
        }
        return result;
    }


    private LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

    private String buildError(String field){
        return String.format(ResourceBundleUtil.getStringValue("MISSING_REQUIRED_FIELD"),field);
    }
}
