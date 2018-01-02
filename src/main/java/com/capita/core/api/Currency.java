package com.capita.core.api;

public enum Currency {
  USD(1), INR(66f), GBP(1.74f), SGP(1.34f);

  public long getConversionRate() {
    return (long) conversionRate;
  }

  private float conversionRate;

  Currency(float conversion) {
    this.conversionRate = conversion;
  }
}
