package com.example.service

import com.example.entity.Stock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.example.repository.StockRepository

@Service
class StockService {

    @Autowired
    private lateinit var stockRepository : StockRepository

    fun addStock(stock: Stock) {
        stockRepository.save(stock);
    }

    fun getStock(stockName : String) : Stock? {
        return stockRepository.findByCompanyName(stockName)
    }

    fun buy(stockI: Stock) {
        val stock = stockRepository.findByCompanyName(stockI.companyName)
        if (stock == null) {
            stockRepository.save(stock);
        } else {
            stock.count += stockI.count
            stockRepository.save(stock);
        }
    }

    fun changeStock(stockI: Stock) {
        val stock = stockRepository.findByCompanyName(stockI.companyName)
        if (stock == null) {
            stockRepository.save(stock);
        } else {
            stock.price += stockI.price
            stockRepository.save(stock);
        }
    }
}