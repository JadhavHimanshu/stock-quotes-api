package org.dnyanyog.mapper;

import org.dnyanyog.dto.StockData;
import org.dnyanyog.dto.StockResponse;

public class StockMapper {
  public static StockResponse fromIexQuote(StockData data) {
    Double percentChange = data.getPercentChange() != null ? data.getPercentChange() : 0.0;

    return new StockResponse(
        data.getSymbol(),
        data.getPrice(),
        data.getChange(),
        percentChange * 100.00,
        data.getTimestamp());
  }
}
