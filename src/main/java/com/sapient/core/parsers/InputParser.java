package com.sapient.core.parsers;

import com.sapient.core.api.Income;
import java.io.File;
import java.util.List;

public interface InputParser {
  List<Income> parseIncome(File fileName);
}
