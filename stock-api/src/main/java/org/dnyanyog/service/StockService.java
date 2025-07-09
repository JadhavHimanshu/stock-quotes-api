package org.dnyanyog.service;

import java.util.List;
import org.dnyanyog.dto.StockResponse;
import org.springframework.stereotype.Service;

@Service
public interface StockService {
  StockResponse getQuote(String symbol);

  List<StockResponse> getBatchQuotes(List<String> symbols);
}
