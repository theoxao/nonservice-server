package com.theoxao.packageless

import com.theoxao.entities.RouteEntity
import com.theoxao.common.GroovyShellHolder
import com.theoxao.service.RouteHandler

abstract class FileRouteLoader(private val routeHandler: RouteHandler) : RouteLoader(routeHandler) {
    override fun routeSupplier(): List<RouteEntity>? {
        val script = GroovyShellHolder.shell.parse(getScriptData())
        //FILLME
        return null
    }

    abstract fun getScriptData(): String

}