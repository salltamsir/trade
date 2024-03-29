package org.ibanfirst.trade.response;

import lombok.*;
import org.ibanfirst.trade.model.Trade;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeResponse {

    private String reference;
    private LocalDate tradeDate;
    private String symbol;
    private String currency;
    private Float amount;
    private Float amountCounterValue;
    private Float rate;
    private LocalDate valueDate;
    public static TradeResponse fromTrade(Trade trade){
        return TradeResponse.builder()
                .reference(trade.getReference())
                .tradeDate(trade.getTradeDate())
                .symbol(trade.getSymbol())
                .currency(trade.getCurrency())
                .amount(trade.getAmount())
                .amountCounterValue(trade.getAmountCounterValue())
                .rate(trade.getRate())
                .valueDate(trade.getValueDate())
                .build();
    }
}
