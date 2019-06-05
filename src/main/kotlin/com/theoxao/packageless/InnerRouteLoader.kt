package com.theoxao.packageless

import com.theoxao.entities.RouteEntity
import com.theoxao.entities.InnerRouteEntity
import com.theoxao.service.RouteHandler
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component


/**
 * create by theoxao on 2019/5/24
 */
@Component
class InnerRouteLoader(private val mongoTemplate: MongoTemplate, private val routeHandler: RouteHandler) : RouteLoader(routeHandler) {
    init {
        load()
    }

    override fun routeSupplier(): List<RouteEntity>? =
            mongoTemplate.findAll(InnerRouteEntity::class.java) ?: null

}