package com.theoxao.route

import com.theoxao.entities.RouteEntity
import com.theoxao.repository.RouteRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * @author theo
 * @date 2019/5/20
 */
@RestController
@RequestMapping("/")
class ManageRoute (private val routeRepository: RouteRepository){

    @RequestMapping("/add")
    fun addRoute(path:String , script:String){
        val data = RouteEntity()
        data.path= path
        data.script= script
        routeRepository.save(data)
    }

    @RequestMapping("/remove")
    fun removeRoute(id:String ){
        routeRepository.remove(id)
    }

}