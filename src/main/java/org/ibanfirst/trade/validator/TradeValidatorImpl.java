package org.ibanfirst.trade.validator;

import java.time.LocalDate;

public class TradeValidatorImpl implements TradeValidator {
    @Override
    public String validateReference(String reference) {
        return null;
    }
    @Override
    public LocalDate validateDate(String dateStr) {
        return null;
    }

    @Override
    public String validateSymbol(String symbol) {
        return null;
    }

    @Override
    public String validateCurrency(String currency) {
        return null;
    }
    @Override
    public double validateDouble(String doubleStr, String message) {
        return 0;
    }
}
