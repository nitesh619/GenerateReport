package com.capita.core.api;


public class Income {
  private String country;
  private String city;
  private String gender;

  public Income(final String country, final String city, final String gender,
      final Currency currency,
      final long avgIncome) {
    this.country = country;
    this.city = city;
    this.gender = gender;
    this.currency = currency;
    this.avgIncome = avgIncome;
  }

  private Currency currency;
  private long avgIncome;

  @Override
  public String toString() {
    return "Income{" +
        "country='" + country + '\'' +
        ", city='" + city + '\'' +
        ", gender='" + gender + '\'' +
        ", currency=" + currency +
        ", avgIncome='" + avgIncome + '\'' +
        '}';
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(final String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(final String city) {
    this.city = city;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(final String gender) {
    this.gender = gender;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(final Currency currency) {
    this.currency = currency;
  }

  public long getAvgIncome() {
    return avgIncome;
  }

  public void setAvgIncome(final long avgIncome) {
    this.avgIncome = avgIncome;
  }
}
