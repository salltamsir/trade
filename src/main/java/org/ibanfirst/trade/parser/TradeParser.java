package org.ibanfirst.trade.parser;

import org.ibanfirst.trade.model.Trade;

public class TradeParser {

    public static Trade createFromString(String text){
        return Trade.builder().build();
    }
}
