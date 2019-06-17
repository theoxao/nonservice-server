package com.theoxao.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.theoxao.annotations.ShylyService
import com.theoxao.common.Constant.ROUTE_DATA_REDIS_PREFIX
import com.theoxao.entities.RouteEntity
import org.bson.types.ObjectId
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.util.Assert
import java.util.concurrent.ConcurrentHashMap


/**
 * @author theo
 * @date 2019/5/23
 */
@ShylyService
class RouteCacheService(private val redisTemplate: StringRedisTemplate) {

    val routeCache: ConcurrentHashMap<String, RouteEntity> = ConcurrentHashMap()
    val scriptCache: ConcurrentHashMap<String, RouteEntity> = ConcurrentHashMap()

    fun updateScript(id: String, scriptId: ObjectId) {
        Assert.isTrue(routeCache.containsKey(id), "route does not exist")
        val raw = redisTemplate.boundValueOps(ROUTE_DATA_REDIS_PREFIX + id).get()
        val objectMapper = ObjectMapper()
        val data = objectMapper.readValue<RouteEntity>(raw, RouteEntity::class.java)
        data.scriptId = scriptId
        redisTemplate.boundValueOps(ROUTE_DATA_REDIS_PREFIX + id).set(objectMapper.writeValueAsString(data))
        routeCache[id]!!.scriptId = scriptId
    }
}