package com.capita.core.report;

import static java.util.stream.Collectors.averagingLong;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toMap;

import com.capita.core.api.Income;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GenerateReport {

  private static final String OUTPUT_HEAD_COLUMNS = "Country\\City,Gender,Average Income(USD)";

  public void writeReport(List<Income> incomeList, String reportName) {
    try (FileWriter fw = new FileWriter(new File(reportName))) {
      fw.append(OUTPUT_HEAD_COLUMNS).append("\n");
      LinkedHashMap<String, Map<String, Double>> avgGenderIncomeByCountry =
          calculateAvgIncomePerCountryByGender(incomeList);

      fw.append(joinCountryGenderAvgIncome(avgGenderIncomeByCountry));
    } catch (IOException e) {
      System.out.println("Can't generate report: " + e.getMessage());
    }
  }

  private String joinCountryGenderAvgIncome(
      final LinkedHashMap<String, Map<String, Double>> avgGenderIncomeByCountry) {
    return avgGenderIncomeByCountry.entrySet()
        .stream()
        .map(countryEntry -> countryEntry.getValue().entrySet()
                .stream()
                .map(genderEntry -> joinKeyValues(countryEntry, genderEntry))
                .collect(joining("\n")))
        .collect(joining("\n"));
  }

  private LinkedHashMap<String, Map<String, Double>> calculateAvgIncomePerCountryByGender(
      final List<Income> incomeList) {
    return incomeList.stream()
        .collect(groupingBy(Income::getCountry,
            groupingBy(Income::getGender,
                mapping(this::getIncomeInUSDollars,
                    averagingLong(Long::valueOf)))))
        .entrySet()
        .stream()
        .sorted(Entry.comparingByKey())
        .collect(toMap(Entry::getKey, Entry::getValue,
            (m1, m2) -> m1,
            LinkedHashMap::new));
  }

  private long getIncomeInUSDollars(final Income income) {
    return income.getAvgIncome() / income.getCurrency().getConversionRate();
  }

  private String joinKeyValues(final Entry<String, Map<String, Double>> countryEntry,
      final Entry<String, Double> genderEntry) {
    return countryEntry.getKey() + "," + genderEntry.getKey() + "," + genderEntry.getValue();
  }
  // only sorting
//  public void sortByCountryGenderIncome(List<Income> incomeList) {
//    incomeList.sort(Comparator.comparing(Income::getCountry).thenComparing(Income::getGender)
//        .thenComparing(Income::getAvgIncome));
//  }
}
