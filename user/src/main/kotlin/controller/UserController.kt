package controller

import dto.UserBuy
import com.example.entity.Stock
import entity.User
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import service.UserService

@RestController
class UserController {

    @Autowired
    private lateinit var userService : UserService

    @PostMapping("/addUser")
    fun addUser(@RequestBody user : User) {
        userService.addUser(user);
    }

    @GetMapping("/getUserStocks")
    fun getStocks(@RequestBody id : Long) : List<Stock> {
        return userService.getStocks(id);
    }

    @PostMapping("/buyStock")
    fun buyStock(@RequestBody userBuy : UserBuy) {
        userService.buy(userBuy);
    }

}