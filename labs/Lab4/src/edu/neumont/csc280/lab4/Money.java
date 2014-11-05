package edu.neumont.csc280.lab4;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

// found at http://stackoverflow.com/questions/1359817/using-bigdecimal-to-work-with-currencies

public class Money {

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

    @Override
    public String toString() {
        return /*getCurrency().getSymbol() + " " + */ "" + getAmount();
    }

    public String toString(Locale locale) {
        return getCurrency().getSymbol(locale) + " " + getAmount();
    }
}
