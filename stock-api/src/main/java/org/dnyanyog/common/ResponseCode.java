package org.dnyanyog.common;

public enum ResponseCode {
  FETCH_STOCK_SUCCESS("200", "Stock quote fetched successfully"),
  STOCK_NOT_FOUND("404", "Stock quote not found"),
  INVALID_SYMBOL("400", "Invalid stock symbol"),
  EXTERNAL_API_ERROR("502", "Failed to fetch data from external API"),
  INTERNAL_SERVER_ERROR("500", "Unexpected server error");

  private final String code;
  private final String message;

  ResponseCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
