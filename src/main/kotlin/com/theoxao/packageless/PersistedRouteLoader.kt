package com.theoxao.packageless

import com.fasterxml.jackson.databind.ObjectMapper
import com.theoxao.common.BaseRouteData
import com.theoxao.common.Constant
import com.theoxao.service.RouteHandler
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component


/**
 * create by theoxao on 2019/5/24
 */
@Component
class PersistedRouteLoader(private val routeHandler: RouteHandler, private val redisTemplate: StringRedisTemplate) : RouteLoader(routeHandler) {

    override fun routeSupplier(): List<BaseRouteData> =
            redisTemplate.keys("${Constant.ROUTE_DATA_REDIS_PREFIX}*").map {
                val routeData = redisTemplate.boundValueOps(it).get()
                ObjectMapper().readValue<BaseRouteData>(routeData, BaseRouteData::class.java)
            }


}