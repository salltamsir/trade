package org.ibanfirst.trade.parser;

import org.ibanfirst.trade.config.Config;
import org.ibanfirst.trade.model.Trade;
import org.ibanfirst.trade.utils.Constants;
import org.ibanfirst.trade.validator.TradeValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TradeParser {

    /**
     * Converts a text representing an email into a Trade object.
     *
     * @param text      The text representing the email.
     * @param validator The validator of the mail content.
     * @return A Trade object created from the extracted data.
     */
    public static Trade tradeFromString(String text, TradeValidator validator){

        Trade trade = new Trade();

        trade.setReference(validator.validateReference(findPattern(Config.getProperty(Constants.REFERENCE_REGEX), text)));
        trade.setTradeDate(validator.validateDate(findPattern(Config.getProperty(Constants.TRADE_DATE_REGEX), text), "trade date"));
        String currency = validator.validateCurrency(findPattern(Config.getProperty(Constants.CURRENCY_REGEX), text));
        trade.setCurrency(currency);
        String buyCurrency = findPattern(Config.getProperty(Constants.BUY_CURRENCY_REGEX), text);
        trade.setSymbol(validator.validateSymbol(buyCurrency + currency));
        trade.setAmount(validator.validateFloat(toDoubleFormat(findPattern(Config.getProperty(Constants.AMOUNT_SELL_REGEX), text)), "amount"));
        trade.setAmountCounterValue(validator.validateFloat(toDoubleFormat(findPattern(Config.getProperty(Constants.AMOUNT_BUY_REGEX), text)),"amount counter value"));
        trade.setRate(validator.validateFloat(toDoubleFormat(findPattern(Config.getProperty(Constants.RATE_REGEX), text)), "rate"));
        trade.setValueDate(validator.validateDate(findPattern(Config.getProperty(Constants.VALUE_DATE_REGEX), text), "value date"));

        return trade;

    }
    /**
     * Finds a substring in the given text that matches the specified regular expression.
     *
     * @param regex The regular expression pattern.
     * @param text  The input text to search within.
     * @return The substring corresponding to the regex if found, or an empty string if not found.
     */
    private static String findPattern(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
    private static String toDoubleFormat(String numberStr) {
        return numberStr.replace(",", "");
    }
}
