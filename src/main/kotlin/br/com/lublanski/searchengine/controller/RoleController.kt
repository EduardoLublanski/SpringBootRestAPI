package br.com.lublanski.searchengine.controller

import br.com.lublanski.searchengine.datamap.Role
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import br.com.lublanski.searchengine.service.RoleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/role")
class RoleController(val roleService : RoleService) {

    @PostMapping("/Register")
    fun register(@RequestBody @Validated role: Role) : ResponseEntity<Role>{

        val newRole = roleService.register(role)

        return ResponseEntity.status(HttpStatus.CREATED).body(role)
    }

    @PostMapping()
    fun list() : List<Role> {

        return roleService.listAllRoles()

    }



}