package com.theoxao.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.theoxao.annotations.ShylyService
import com.theoxao.common.BaseRouteData
import com.theoxao.common.Constant.ROUTE_DATA_REDIS_PREFIX
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.util.Assert
import java.util.concurrent.ConcurrentHashMap


/**
 * @author theo
 * @date 2019/5/23
 */
@ShylyService
class RouteCacheService(private val redisTemplate: StringRedisTemplate) {

    val cache: ConcurrentHashMap<String, BaseRouteData> = ConcurrentHashMap()

    fun updateScript(id: String, script: String) {
        Assert.isTrue(cache.containsKey(id), "route does not exist")
        val raw = redisTemplate.boundValueOps(ROUTE_DATA_REDIS_PREFIX + id).get()
        val objectMapper = ObjectMapper()
        val data = objectMapper.readValue<BaseRouteData>(raw, BaseRouteData::class.java)
        data.script = script
        redisTemplate.boundValueOps(ROUTE_DATA_REDIS_PREFIX + id).set(objectMapper.writeValueAsString(data))
        cache[id]!!.script = script
    }
}