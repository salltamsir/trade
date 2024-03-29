package org.ibanfirst.trade.model;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trade {

    private String reference;
    private LocalDate tradeDate;
    private String symbol;
    private String currency;
    private Float amount;
    private Float amountCounterValue;
    private Float rate;
    private LocalDate valueDate;


}
