package com.theoxao.packageless

import com.fasterxml.jackson.databind.ObjectMapper
import com.theoxao.common.BaseRouteData
import com.theoxao.common.Constant.ROUTE_DATA_REDIS_PREFIX
import com.theoxao.service.RouteHandler
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component


/**
 * load exist route when project init
 *
 * @author theo
 * @date 2019/5/22
 */

@Component
class InitRouteLoader(private val redisTemplate: StringRedisTemplate, private val routeHandler: RouteHandler) {
    //TODO should read route data from redis or persist db?
    init {
        redisTemplate.keys("$ROUTE_DATA_REDIS_PREFIX*").forEach {
            val routeData = redisTemplate.boundValueOps(it).get()
            val route = ObjectMapper().readValue<BaseRouteData>(routeData, BaseRouteData::class.java)
            routeHandler.addRoute(route)
        }
    }

}