package com.capita.core.parsers;

import com.capita.core.api.Income;
import java.io.File;
import java.util.List;

public interface InputParser {

  List<Income> parseIncome(File fileName);
}
