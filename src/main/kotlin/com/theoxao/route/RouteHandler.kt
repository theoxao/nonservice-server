package com.theoxao.route

import com.theoxao.common.CommonResult
import com.theoxao.common.ParamWrap
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.routing
import io.ktor.util.AttributeKey


/**
 * create by theoxao on 2019/5/18
 */
interface RouteHandler {
    fun addRoute(routeData: BaseRouteData)
    fun removeRoute(id: String)
}