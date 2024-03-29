package paser;

import org.ibanfirst.trade.validator.TradeValidator;
import org.ibanfirst.trade.validator.TradeValidatorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TradeParserTest {
    private TradeValidator validator = new TradeValidatorImpl();
    @Test
    void testValidateReference_ValidReference() {
        String reference = "12345678";
        String result = validator.validateReference(reference);
        Assertions.assertEquals(reference, result);
    }

    @Test
    void testValidateReference_NullReference() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validator.validateReference(null));
        assertEquals("Missing field reference", exception.getMessage());
    }

    @Test
    void testValidateDate_ValidDate() {

        String date = "05/08/2021";
        LocalDate result = validator.validateDate(date, "date");
        Assertions.assertEquals(LocalDate.of(2021, 8, 5), result);
    }

    @Test
    void testValidateDate_InvalidDate() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validator.validateDate("invalid", "trade date"));
        assertEquals("Missing required field trade date", exception.getMessage());
    }

    @Test
    void testValidateSymbol_ValidSymbol() {

        String symbol = "EURXOF";
        String result = validator.validateSymbol(symbol);
        Assertions.assertEquals(symbol, result);
    }


    @Test
    void testValidateCurrency_ValidCurrency() {

        String currency = "USD";
        String result = validator.validateCurrency(currency);
        Assertions.assertEquals(currency, result);
    }

    @Test
    void testValidateCurrency_InvalidCurrency() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validator.validateCurrency(null));
        assertEquals("Missing required field currency", exception.getMessage());

    }

    @Test
    void testValidateDouble_ValidDouble() {

        String doubleStr = "10.5";
        double result = validator.validateFloat(doubleStr, "error_message");
        Assertions.assertEquals(10.5, result);
    }

    @Test
    void testValidateDouble_InvalidDouble() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validator.validateFloat("invalid double", "amount"));
        assertEquals("Missing required field amount", exception.getMessage());
    }
}
