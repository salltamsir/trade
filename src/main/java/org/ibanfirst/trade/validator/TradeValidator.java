package org.ibanfirst.trade.validator;

import java.time.LocalDate;

public interface TradeValidator {

    String validateReference(String reference);
    LocalDate validateDate(String dateStr);
    String validateSymbol(String symbol);
    String validateCurrency(String currency);
    double validateDouble(String doubleStr, String message);
}
