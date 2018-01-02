package com.sapient.core;

import com.sapient.core.api.Income;
import com.sapient.core.parsers.CSVParser;
import com.sapient.core.report.GenerateReport;
import java.io.File;
import java.util.List;

public class EntryPoint {

  private static final String INPUT_FILE_PATH = "D:\\Project\\git projects\\PerCapitaIncome\\src\\main\\resources\\Sample_Input.csv";
  private static List<Income> incomeList;

  public static void main(String[] args) {
    EntryPoint enp = new EntryPoint();
    File file = enp.openFile(INPUT_FILE_PATH);

    if (file.exists()) {
      CSVParser csvParser = new CSVParser();
      incomeList = csvParser.parseIncome(file);

      printIncomes();
    }
    GenerateReport generateReport = new GenerateReport();
//  generateReport.sortByCountryGenderIncome(incomeList);

    generateReport.writeReport(incomeList, "output.csv");
  }

  private static void printIncomes() {
    for (Income income : incomeList) {
      System.out.println(income);
    }
  }

  public File openFile(String fileName) {
    if (fileName == null || fileName.isEmpty()) {
      System.out.println("Invalid file name");
      System.exit(0);
    }
    return new File(fileName);
  }
}
