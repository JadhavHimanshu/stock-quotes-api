package org.dnyanyog.service;

import java.util.ArrayList;
import java.util.List;
import org.dnyanyog.dto.StockData;
import org.dnyanyog.dto.StockResponse;
import org.dnyanyog.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StockServiceImpl implements StockService {

  private final WebClient webClient;

  @Value("${eodhd.token}")
  private String apiToken;

  public StockServiceImpl(@Value("${eodhd.base-url}") String baseUrl) {
    this.webClient = WebClient.builder().baseUrl(baseUrl).build();
  }

  public StockResponse getQuote(String symbol) {
    try {
      String marketSymbol = symbol + ".US";
      Mono<StockData> response =
          webClient
              .get()
              .uri(
                  uriBuilder ->
                      uriBuilder
                          .path("/api/real-time/{symbol}")
                          .queryParam("api_token", apiToken)
                          .queryParam("fmt", "json")
                          .build(marketSymbol))
              .retrieve()
              .bodyToMono(StockData.class);

      StockData quote = response.block();
      return (quote != null) ? StockMapper.fromIexQuote(quote) : null;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<StockResponse> getBatchQuotes(List<String> symbols) {
    List<StockResponse> list = new ArrayList<>();

    try {
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < symbols.size(); i++) {
        builder.append(symbols.get(i)).append(".US");
        if (i < symbols.size() - 1) {
          builder.append(",");
        }
      }

      String marketSymbols = builder.toString();

      Mono<StockData[]> response =
          webClient
              .get()
              .uri(
                  uriBuilder ->
                      uriBuilder
                          .path("/api/real-time/{symbols}")
                          .queryParam("api_token", apiToken)
                          .queryParam("fmt", "json")
                          .build(marketSymbols))
              .retrieve()
              .bodyToMono(StockData[].class);

      StockData[] quotes = response.block();

      if (quotes != null) {
        for (int i = 0; i < quotes.length; i++) {
          list.add(StockMapper.fromIexQuote(quotes[i]));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
}
