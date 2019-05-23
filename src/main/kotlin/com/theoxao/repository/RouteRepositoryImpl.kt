package com.theoxao.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.theoxao.common.Constant.ROUTE_DATA_REDIS_PREFIX
import com.theoxao.entities.RouteEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository
import org.springframework.util.Assert


/**
 * @author theo
 * @date 2019/5/20
 */
@Repository
open class RouteRepositoryImpl(private val mongoTemplate: MongoTemplate,
                               private val redisTemplate: StringRedisTemplate
) : RouteRepository {

    private val objectMapper = ObjectMapper()

    override fun findById(id: String) {
        mongoTemplate.findById(ObjectId(id), RouteEntity::class.java)
    }

    override fun findAll() {
        mongoTemplate.findAll(RouteEntity::class.java)
    }

    override fun save(route: RouteEntity) {
        if (redisTemplate.hasKey(ROUTE_DATA_REDIS_PREFIX + route.id)) throw RuntimeException("")
        redisTemplate.boundValueOps(ROUTE_DATA_REDIS_PREFIX + route.id)
                .set(objectMapper.writeValueAsString(route))     //TODO move this
        mongoTemplate.save(route)
    }

    override fun update(route: RouteEntity) {

    }

    override fun updateSelective(route: RouteEntity) {
        Assert.notNull(route.id, "route id should null be null")

    }

    override fun remove(id: String) {
        redisTemplate.delete(ROUTE_DATA_REDIS_PREFIX + id)
        mongoTemplate.remove(Query.query(Criteria.where("id").`is`(ObjectId(id))), RouteEntity::class.java)
    }
}