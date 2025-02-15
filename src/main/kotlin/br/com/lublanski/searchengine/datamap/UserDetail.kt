package br.com.lublanski.searchengine.datamap

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val user : User) : UserDetails {
    override fun getAuthorities() = user.roles.map { SimpleGrantedAuthority(it.roleName) }


    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.username
    }

}
