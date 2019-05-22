package com.theoxao.middleware

import com.fasterxml.jackson.databind.ObjectMapper
import com.theoxao.common.Constant.Companion.ROUTE_DATA_REDIS_PREFIX
import com.theoxao.entities.RouteEntity
import com.theoxao.route.RouteHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor


/**
 * create by theoxao on 2019/5/19
 */
@Configuration
open class RedisMessageListener(private val redisTemplate: StringRedisTemplate) {

    @Bean
    open fun container(routeHandler: RouteHandler): RedisMessageListenerContainer {
        val objectMapper = ObjectMapper()
        val container = RedisMessageListenerContainer()
        container.connectionFactory = redisTemplate.connectionFactory
        container.setTaskExecutor(ConcurrentTaskExecutor())
        val topic = PatternTopic("*")
        container.addMessageListener(MessageListener { message, _ ->
            val body = String(message.body)
            val channel = String(message.channel)
            println(body)
            println(channel)
            when {
                //use hash to store route data
                channel.contains("hset") -> {
                    val raw = redisTemplate.boundValueOps(ROUTE_DATA_REDIS_PREFIX + body).get()
                    val node = objectMapper.readTree(raw)
                    val data = RouteEntity()
                    data.id = node.findValue("id").asText()
                    data.path = node.findValue("path").asText()
                    data.script = node.findValue("script").asText()
                    routeHandler.addRoute(data)
                }
                channel.contains("hdel") -> {
                    routeHandler.removeRoute(body)
                }
            }
        }, topic)
        return container
    }

}