package com.theoxao.packageless

import com.fasterxml.jackson.databind.ObjectMapper
import com.theoxao.entities.RouteEntity
import com.theoxao.common.Constant
import com.theoxao.service.RouteHandler
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component


/**
 * create by theoxao on 2019/5/24
 */
@Component
class PersistedRouteLoader(private val routeHandler: RouteHandler, private val redisTemplate: StringRedisTemplate) : RouteLoader(routeHandler) {

    init {
        load()
    }

    override fun routeSupplier(): List<RouteEntity> =
            redisTemplate.keys("${Constant.ROUTE_DATA_REDIS_PREFIX}*").map {
                val routeData = redisTemplate.boundValueOps(it).get()
                ObjectMapper().readValue<RouteEntity>(routeData, RouteEntity::class.java)
            }


}