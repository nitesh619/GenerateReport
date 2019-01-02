package com.capita.core.parsers;

import static com.capita.core.util.ParserConstants.CITY_INDEX;
import static com.capita.core.util.ParserConstants.COUNTRY_INDEX;
import static com.capita.core.util.ParserConstants.CURRENCY_INDEX;
import static com.capita.core.util.ParserConstants.GENDER_INDEX;
import static com.capita.core.util.ParserConstants.INCOME_INDEX;

import com.capita.core.api.Currency;
import com.capita.core.api.Income;
import java.util.Spliterator;
import java.util.function.Consumer;

public class IncomeSplitIterator implements Spliterator<Income> {

    private Spliterator<String> spliterator;
    private String[] incomeToken;

    public IncomeSplitIterator(final Spliterator<String> spliterator) {
        this.spliterator = spliterator;
        spliterator.tryAdvance(s -> System.out.println("Skip Header: " + s));
    }

    @Override
    public boolean tryAdvance(final Consumer<? super Income> action) {
        if (spliterator.tryAdvance(s -> this.incomeToken = s.split(","))) {
            if (incomeToken.length != 5) {
                action.accept(null);
                return true;
            }
            String country = incomeToken[COUNTRY_INDEX];
            String city = incomeToken[CITY_INDEX];
            String gender = incomeToken[GENDER_INDEX];
            String currency = incomeToken[CURRENCY_INDEX];
            String avgIncome = incomeToken[INCOME_INDEX];
            action.accept(new Income(country, city, gender, Currency.valueOf(currency),
                    Long.valueOf(avgIncome)));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Spliterator<Income> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return spliterator.estimateSize();
    }

    @Override
    public int characteristics() {
        return spliterator.characteristics();
    }
}
