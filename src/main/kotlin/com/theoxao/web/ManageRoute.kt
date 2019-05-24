package com.theoxao.web

import com.theoxao.common.BaseRouteData
import com.theoxao.entities.RouteEntity
import com.theoxao.repository.RouteRepository
import com.theoxao.service.RouteCacheService
import com.theoxao.service.RouteHandler
import com.theoxao.service.RouteService
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * @author theo
 * @date 2019/5/20
 */

//TODO move these route to script
@RestController
@RequestMapping("/")
class ManageRoute(private val routeService: RouteService, private val routeCacheService: RouteCacheService) {
    private val success = "success"

    @RequestMapping("/add")
    fun addRoute(path: String, script: String): String {
        val data = RouteEntity()
        val id = ObjectId().toHexString()
        data.id = id
        data.path = path
        data.script = script
        routeService.addRoute(data)
        return id
    }

    @RequestMapping("/remove")
    fun removeRoute(id: String): String {
        routeService.removeRoute(id)
        return success
    }

    @RequestMapping("/update_script")
    fun updateRoute(id: String, script: String): String {
        routeCacheService.updateScript(id, script)
        return success
    }

}