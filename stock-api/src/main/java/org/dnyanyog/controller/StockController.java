package org.dnyanyog.controller;

import java.util.List;
import org.dnyanyog.common.ApiResponse;
import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.StockResponse;
import org.dnyanyog.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/quotes")
public class StockController {

  @Autowired private StockService stockService;

  @GetMapping(
      path = "/{symbol}",
      produces = {" application/json", "application/xml"})
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ApiResponse<StockResponse>> getStockQuote(@PathVariable String symbol) {
    StockResponse quote = stockService.getQuote(symbol);

    if (quote == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ApiResponse<>(ResponseCode.STOCK_NOT_FOUND, null));
    }

    return ResponseEntity.ok(new ApiResponse<>(ResponseCode.FETCH_STOCK_SUCCESS, quote));
  }

  @GetMapping(produces = {"application/json", "application/xml"})
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ApiResponse<List<StockResponse>>> getBatchQuotes(
      @RequestParam List<String> symbols) {
    List<StockResponse> quotes = stockService.getBatchQuotes(symbols);

    if (quotes == null || quotes.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ApiResponse<>(ResponseCode.STOCK_NOT_FOUND, null));
    }

    return ResponseEntity.ok(new ApiResponse<>(ResponseCode.FETCH_STOCK_SUCCESS, quotes));
  }
}
