package com.theoxao.service

import com.theoxao.annotations.ShylyService
import com.theoxao.common.BaseRouteData


/**
 * create by theoxao on 2019/5/18
 */

interface RouteHandler {
    fun addRoute(routeData: BaseRouteData)
    fun removeRoute(id: String)
}