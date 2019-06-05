package com.theoxao.service

import com.theoxao.entities.RouteEntity


/**
 * create by theoxao on 2019/5/18
 */

interface RouteHandler {
    fun addRoute(routeEntity: RouteEntity)
    fun removeRoute(id: String)
}