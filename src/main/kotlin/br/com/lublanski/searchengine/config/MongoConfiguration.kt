package br.com.lublanski.searchengine.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["br.com.lublanski.searchengine.repository"])
class MongoConfiguration
