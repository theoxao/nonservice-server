package com.theoxao.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.theoxao.common.Constant
import com.theoxao.entities.RouteEntity
import com.theoxao.repository.RouteRepository
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service


/**
 * @author theo
 * @date 2019/5/24
 */
@Service
class RouteService(
        private val routeRepository: RouteRepository,
        private val redisTemplate: StringRedisTemplate
) {
    private val objectMapper = ObjectMapper()

    /**
     * route add service
     */
    fun addRoute(route: RouteEntity) {
        if (redisTemplate.hasKey(Constant.ROUTE_DATA_REDIS_PREFIX + route.id)) throw RuntimeException("duplicate key ")
        //save route info to redis; trigger redis message listener
        redisTemplate.boundValueOps(Constant.ROUTE_DATA_REDIS_PREFIX + route.id)
                .set(objectMapper.writeValueAsString(route))     //TODO move this
        //persist route info
        routeRepository.save(route)
    }

    fun removeRoute(id: String) {
        //remove redis cache; trigger redis message listener
        redisTemplate.delete(Constant.ROUTE_DATA_REDIS_PREFIX + id)
        routeRepository.remove(id)
    }

}