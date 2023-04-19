package com.example.controller

import com.example.entity.Stock
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import com.example.service.StockService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ResponseStatus
import java.net.http.HttpResponse

@RestController
class StockController {

    @Autowired
    private lateinit var stockService : StockService

    @PostMapping("/addStock")
    fun addStock(@RequestBody stock : Stock) : ResponseEntity<String> {
        stockService.addStock(stock)
        return ResponseEntity.ok("added")
    }

    @GetMapping("/getStockData")
    fun getStock(@RequestBody stockName : String) : Stock? {
        return stockService.getStock(stockName);
    }

    @PostMapping("/buyStock")
    fun buyStock(@RequestBody stock : Stock) {
        stockService.buy(stock);
    }

    @PostMapping("/changeStock")
    fun changeStock(@RequestBody stock : Stock) {
        stockService.changeStock(stock);
    }
}