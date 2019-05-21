package com.theoxao.route

import com.theoxao.entities.RouteEntity
import com.theoxao.repository.RouteRepository
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
class ManageRoute(private val routeRepository: RouteRepository) {
    private val success = "success"

    @RequestMapping("/add")
    fun addRoute(path: String, script: String): String {
        val data = RouteEntity()
        val id = ObjectId().toHexString()
        data.id= id
        data.path = path
        data.script = script
        routeRepository.save(data)
        return id
    }

    @RequestMapping("/remove")
    fun removeRoute(id: String): String {
        routeRepository.remove(id)
        return success
    }

}