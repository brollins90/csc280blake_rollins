//package edu.neumont.csc280.lab6;
//
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//
///**
//* Created by blakerollins on 11/26/14.
//*/
//
//@Converter(autoApply = true)
//public class MoneyToDoubleConverter implements AttributeConverter<Money, Double> {
//
//    @Override
//    public Double convertToDatabaseColumn(Money m) {
//        return m.getAmount().doubleValue();
//    }
//
//    @Override
//    public Money convertToEntityAttribute(Double d) {
//        return Money.dollars(d);
//    }
//}
