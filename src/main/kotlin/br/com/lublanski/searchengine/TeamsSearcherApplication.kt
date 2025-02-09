package br.com.lublanski.searchengine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BuscadorApplication

fun main(args: Array<String>) {
	runApplication<BuscadorApplication>(*args)
}
