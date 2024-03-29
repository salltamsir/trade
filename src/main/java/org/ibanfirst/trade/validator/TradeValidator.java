package org.ibanfirst.trade.validator;

import java.time.LocalDate;

public interface TradeValidator {

    String validateReference(String reference);
    LocalDate validateDate(String dateStr, String field);
    String validateSymbol(String symbol);
    String validateCurrency(String currency);
    float validateFloat(String floatStr, String field);
}
