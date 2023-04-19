package entity

import com.example.entity.Stock
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long = 0,
    var name : String = "",

    @OneToMany
    var stocks : List<Stock> = mutableListOf(),

    var money : Double
)