package edu.neumont.csc280.lab5;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

// found at http://stackoverflow.com/questions/1359817/using-bigdecimal-to-work-with-currencies

public class Money implements Comparable {

    private static final Currency USD = Currency.getInstance("USD");
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

    private BigDecimal amount;
    private Currency currency;

    public static Money dollars(Double amount) {
        return new Money(new BigDecimal(amount), USD);
    }

    public static Money dollars(BigDecimal amount) {
        return new Money(amount, USD);
    }

    private Money(BigDecimal amount, Currency currency) {
        this(amount, currency, DEFAULT_ROUNDING);
    }

    private Money(BigDecimal amount, Currency currency, RoundingMode rounding) {
        this.amount = amount;
        this.currency = currency;

        this.amount = amount.setScale(currency.getDefaultFractionDigits(), rounding);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

//    @Override
//    public String toString() {
//        return getCurrency().getSymbol() + " " + getAmount();
//    }
//
//    public String toString(Locale locale) {
//        return getCurrency().getSymbol(locale) + " " + getAmount();
//    }

    @Override
    public String toString() {
        return getAmount().toString();
    }

    @Override
    public int compareTo(Object o) {
        return getAmount().compareTo(((Money)o).getAmount());
    }
}
