package validator;

import org.ibanfirst.trade.model.Trade;
import org.ibanfirst.trade.parser.TradeParser;
import org.ibanfirst.trade.validator.TradeValidatorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TradeValidatorImplTest {
    private TradeParser tradeParser = new TradeParser(new TradeValidatorImpl());
    @Test
    void testFromString_ValidTrade_624916332() {
        String text = """
                Subject:
                FOREX Deal - 03/08/2021 (OUR REF : 624916332)
                                
                Body:
                FROM: PARTNER LTD, LONDON, UK
                TO: IBANFIRST SA
                                
                                
                HEREBY WE CONFIRM FOREX DD: 03/08/2021
                WE SELL: CNH 300,864.14
                WE BUY: EUR 39,188.54
                RATE: 7.67735000
                VALUE DATE: 05/08/2021
                OUR PAYMENT: SSI
                YOUR PAYMENT: SSI
                                
                                
                            ***  This is an auto-generated system message ***
                                
                Please do not reply to this message. Messages received at this address will not be processed.
                                
                Kind Regards,
                """;

        Trade trade = tradeParser.tradeFromString(text);

        Assertions.assertNotNull(trade);

        assertEquals("624916332", trade.getReference());
        assertEquals(LocalDate.of(2021,8,3), trade.getTradeDate());
        assertEquals("EURCNH", trade.getSymbol());
        assertEquals("CNH", trade.getCurrency());
        assertEquals(300864.14F, trade.getAmount());
        assertEquals(39188.54F, trade.getAmountCounterValue());
        assertEquals(7.67735F, trade.getRate());
        assertEquals(LocalDate.of(2021,8,5), trade.getValueDate());
    }

    @Test
    void testFromString_ValidTrade_624916107() {
        String text = """
                Subject:
                FOREX Deal - 03/08/2021 (OUR REF : 624916107)
                                
                Body:
                FROM: PARTNER LTD, LONDON, UK
                TO: IBANFIRST SA
                                
                                
                HEREBY WE CONFIRM FOREX DD: 03/08/2021
                WE SELL: EUR 65,948.79
                WE BUY: USD 78,361.67
                RATE: 1.18822000
                VALUE DATE: 05/08/2021
                OUR PAYMENT: SSI
                YOUR PAYMENT: SSI
                                
                                
                            ***  This is an auto-generated system message ***
                                
                Please do not reply to this message. Messages received at this address will not be processed.
                                
                Kind Regards,
                                
                """;

        Trade trade = tradeParser.tradeFromString(text);

        Assertions.assertNotNull(trade);

        assertEquals("624916107", trade.getReference());
        assertEquals(LocalDate.of(2021,8,3), trade.getTradeDate());
        assertEquals("USDEUR", trade.getSymbol());
        assertEquals("EUR", trade.getCurrency());
        assertEquals(65948.79F, trade.getAmount());
        assertEquals(78361.67F, trade.getAmountCounterValue());
        assertEquals(1.18822F, trade.getRate());
        assertEquals(LocalDate.of(2021,8,5), trade.getValueDate());
    }

    @Test
    void testFromString_InvalidReference(){
        String text = """
                Subject:
                FOREX Deal - 03/08/2021 (OUR REF : )
                                
                Body:
                FROM: PARTNER LTD, LONDON, UK
                TO: IBANFIRST SA
                                
                                
                HEREBY WE CONFIRM FOREX DD: 03/08/2021
                WE SELL: EUR 65,948.79
                WE BUY: USD 78,361.67
                RATE: 1.18822000
                VALUE DATE: 05/08/2021
                OUR PAYMENT: SSI
                YOUR PAYMENT: SSI
                                
                                
                            ***  This is an auto-generated system message ***
                                
                Please do not reply to this message. Messages received at this address will not be processed.
                                
                Kind Regards,
                                
                """;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->tradeParser.tradeFromString(text));
        assertEquals("Missing required field reference", exception.getMessage());

    }

    @Test
    void testFromStringInvalidCurrency(){
        String text = """
                Subject:
                FOREX Deal - 03/08/2021 (OUR REF : 6573736)
                                
                Body:
                FROM: PARTNER LTD, LONDON, UK
                TO: IBANFIRST SA
                                
                                
                HEREBY WE CONFIRM FOREX DD: 03/08/2021
                WE SELL: EURXOF 65,948.79
                WE BUY: USD 78,361.67
                RATE: 1.18822000
                VALUE DATE: 05/08/2021
                OUR PAYMENT: SSI
                YOUR PAYMENT: SSI
                                
                                
                            ***  This is an auto-generated system message ***
                                
                Please do not reply to this message. Messages received at this address will not be processed.
                                
                Kind Regards,
                                
                """;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->tradeParser.tradeFromString(text));
        assertEquals("Missing required field currency", exception.getMessage());
    }
}
