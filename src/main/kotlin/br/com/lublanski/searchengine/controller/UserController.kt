package br.com.lublanski.searchengine.controller

import br.com.lublanski.searchengine.datamap.User
import br.com.lublanski.searchengine.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/user")
class UserController(val userService: UserService) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@Validated @RequestBody user: User): ResponseEntity<User> {

        userService.createUser(user)

        return ResponseEntity.status(HttpStatus.CREATED).body(user)


    }
    @GetMapping("/{userId}")
    fun searchById(@PathVariable userId: String): Optional<User> {

        return userService.getUserById(userId)

    }

    @GetMapping
    fun getUsers() : List<User> {

        return userService.getAllUsers()

    }
    @PostMapping("/{userId}/role-register")
    fun userRoleAssign(
        @PathVariable userId : String,
        @RequestParam(required = true) roleName : String
    ) : ResponseEntity<User> {

        val user = userService.addRoleToUser(userId,roleName)

        return ResponseEntity.status(HttpStatus.OK).body(user)
    }

}