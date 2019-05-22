package com.theoxao.route

import com.fasterxml.jackson.databind.ObjectMapper
import com.theoxao.common.Constant.Companion.ROUTE_DATA_REDIS_PREFIX
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

    init {
        redisTemplate.keys("$ROUTE_DATA_REDIS_PREFIX*").forEach {
            val routeData = redisTemplate.boundValueOps(it).get()
            val route = ObjectMapper().readValue<BaseRouteData>(routeData, BaseRouteData::class.java)
            routeHandler.addRoute(route)
        }
    }

}