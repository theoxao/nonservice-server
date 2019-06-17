package com.theoxao.repository

import com.theoxao.entities.RouteEntity
import org.springframework.stereotype.Repository


/**
 * @author theo
 * @date 2019/5/20
 */
interface RouteRepository {

    fun findById(id: String): RouteEntity?

    fun findAll(): MutableList<RouteEntity>

    fun save(route: RouteEntity)

    fun update(route: RouteEntity)

    fun updateSelective(route: RouteEntity)

    fun remove(id: String)

}