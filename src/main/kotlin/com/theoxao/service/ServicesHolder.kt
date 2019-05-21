package com.theoxao.service

import com.theoxao.annotations.EmbeddedService
import io.ktor.client.HttpClient
import org.springframework.context.ApplicationContext
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service


/**
 * @author theo
 * @date 2019/5/20
 */
@Service
class ServicesHolder(
        val redisTemplate: StringRedisTemplate,
        private val applicationContext: ApplicationContext,
        val mongoTemplate: MongoTemplate

) {
    private val level = "ALL"
    private val serviceMap: Map<String, Any> = applicationContext.getBeansWithAnnotation(EmbeddedService::class.java)

    init {
        println(1)
    }

    private val httpClient = HttpClient()
    fun getService(serviceName: String) = serviceMap[serviceName]
}