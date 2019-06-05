package com.theoxao.packageless

import com.theoxao.service.RouteHandler
import org.springframework.stereotype.Component


/**
 * @author theo
 * @date 2019/6/5
 */
@Component
class GitFileRouteLoader(private val routeHandler: RouteHandler) : FileRouteLoader(routeHandler) {
    override fun getScriptData(): String {
        return "file from git"
    }
}