package com.capita.core.parsers;

import static com.capita.core.util.ParserConstants.CITY_INDEX;
import static com.capita.core.util.ParserConstants.COUNTRY_INDEX;
import static com.capita.core.util.ParserConstants.CSV_DELIM;
import static com.capita.core.util.ParserConstants.CURRENCY_INDEX;
import static com.capita.core.util.ParserConstants.GENDER_INDEX;
import static com.capita.core.util.ParserConstants.INCOME_INDEX;

import com.capita.core.api.Currency;
import com.capita.core.api.Income;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser implements InputParser {

  public List<Income> parseIncome(final File fileName) {
    List<Income> incomeList = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      //read and ignore csv column header
      reader.readLine();
      String line;
      //read csv line by line
      while ((line = reader.readLine()) != null) {
        String[] incomeToken = line.split(CSV_DELIM);
        String country = incomeToken[COUNTRY_INDEX];
        String city = incomeToken[CITY_INDEX];
        String gender = incomeToken[GENDER_INDEX];
        String currency = incomeToken[CURRENCY_INDEX];
        String avgIncome = incomeToken[INCOME_INDEX];
        incomeList.add(new Income(country, city, gender, Currency.valueOf(currency), Long.valueOf(avgIncome)));
      }
      reader.close();
    } catch (IOException e) {
      System.out.println("Unable to open file: " + e.getMessage());
    }
    return incomeList;
  }
}
