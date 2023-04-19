package com.example.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Stock(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long = 0,
    var companyName : String = "",
    var price : Double = 0.0,
    var count : Long = 0
)