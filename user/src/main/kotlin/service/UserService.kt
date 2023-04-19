package service

import dto.UserBuy
import dto.UserIncome
import com.example.entity.Stock
import com.fasterxml.jackson.databind.ObjectMapper
import entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import repository.UserRepository
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Service
class UserService {

    @Autowired
    private lateinit var userRepository : UserRepository

    val objectMapper = ObjectMapper()

    val client = HttpClient.newBuilder().build();

    fun addUser(user: User) {
        userRepository.save(user);
    }

    fun addMoney(userIncome: UserIncome)  {
        val user = userRepository.findUserById(userIncome.id)
        if (user != null) {
            user.money += userIncome.money
            userRepository.save(user);
        }
    }

    fun getStocks(id: Long) : List<Stock> {
        val user = userRepository.findUserById(id)
        if (user != null) {
            return user.stocks
        } else {
            return emptyList()
        }
    }

    fun buy(userBuy : UserBuy): Boolean {
        val user = userRepository.findUserById(userBuy.id) ?: throw IllegalArgumentException("No such user!")
        val requestPrice = HttpRequest.newBuilder()
            .uri(URI.create("localhost:8080/getStockData"))
            .POST(HttpRequest.BodyPublishers.ofString(userBuy.companyName))
            .build()

        val responsePrice = client.send(requestPrice, HttpResponse.BodyHandlers.ofString());

        val price = Integer.parseInt(responsePrice.body()) * 1.0

        if (user.money < price) {
            throw IllegalArgumentException("No enough money!")
        }
        val request = HttpRequest.newBuilder()
            .uri(URI.create("localhost:8080/buyStock"))
            .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(Stock(userBuy.id, userBuy.companyName, price, userBuy.count))))
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode() == 200
    }
}