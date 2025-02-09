package br.com.lublanski.searchengine.repository

import br.com.lublanski.searchengine.datamap.Role

interface UserRepositoryCustom {

    fun addRoleToUser(userId : String, role : Role)

}