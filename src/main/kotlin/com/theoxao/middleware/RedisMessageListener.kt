package com.theoxao.middleware

import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor
import org.springframework.stereotype.Component


/**
 * create by theoxao on 2019/5/19
 */
class RedisMessageListener(private val redisTemplate: RedisTemplate<Any, Any>) {

    fun listen(channelFragment: String, handle: (body: String, channel: String, redisTemplate: RedisTemplate<Any, Any>) -> Unit) {
        val container = RedisMessageListenerContainer()
        container.connectionFactory = redisTemplate.connectionFactory
        container.setTaskExecutor(ConcurrentTaskExecutor())
        val topic = PatternTopic("*")
        container.addMessageListener(MessageListener { message, _ ->
            val body = String(message.body)
            val channel = String(message.channel)
            handle(body, channel, redisTemplate)
        }, topic)
    }

}