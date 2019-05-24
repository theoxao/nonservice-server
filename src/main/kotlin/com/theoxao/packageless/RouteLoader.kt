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

abstract class RouteLoader(private val routeHandler: RouteHandler) {
    init {
        load()
    }

    private fun load() {
        routeSupplier().forEach {
            routeHandler.addRoute(it)
        }
    }

    abstract fun routeSupplier(): List<BaseRouteData>;


}