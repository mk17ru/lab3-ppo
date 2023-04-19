package com.example.repository

import com.example.entity.Stock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StockRepository : JpaRepository<Stock, Long> {
    fun findByCompanyName(companyName : String) : Stock?
}