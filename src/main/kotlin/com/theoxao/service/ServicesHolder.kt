package com.theoxao.service

import com.theoxao.annotations.EmbeddedService
import com.theoxao.annotations.ShylyService
import com.theoxao.wrap.DelayFutureWrap
import com.theoxao.http.HttpClient
import com.theoxao.wrap.HttpClientWrap
import io.ktor.util.KtorExperimentalAPI
import org.springframework.context.ApplicationContext
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.redis.core.StringRedisTemplate


/**
 * @author theo
 * @date 2019/5/20
 */
@ShylyService
@KtorExperimentalAPI
class ServicesHolder constructor(
        val redisTemplate: StringRedisTemplate,
        private val applicationContext: ApplicationContext,
        val mongoTemplate: MongoTemplate,
        val httpClientWrap: HttpClientWrap,
        val delayFuture: DelayFutureWrap,
        val httpClient: HttpClient
) {
    private val level = "ALL"
    private val serviceMap: Map<String, Any> = applicationContext.getBeansWithAnnotation(EmbeddedService::class.java)

    fun getService(serviceName: String) = serviceMap[serviceName]
}