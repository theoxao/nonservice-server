package com.theoxao.packageless

import com.theoxao.common.BaseRouteData
import com.theoxao.service.RouteHandler


/**
 * load exist route when project init
 *
 * @author theo
 * @date 2019/5/22
 */

abstract class RouteLoader(private val routeHandler: RouteHandler) {

    protected fun load() {
        routeSupplier()?.forEach {
            routeHandler.addRoute(it)
        }
    }

    abstract fun routeSupplier(): List<BaseRouteData>?


}