package com.theoxao.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.theoxao.entities.RouteEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository


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
        redisTemplate.boundValueOps(route.id).set(objectMapper.writeValueAsString(route))
        mongoTemplate.save(route)
    }

    override fun update(route: RouteEntity) {
    }

    override fun updateSelective(route: RouteEntity) {
    }

    override fun remove(id: String) {
        redisTemplate.delete(id)
        mongoTemplate.remove(Query.query(Criteria.where("id").`is`(ObjectId(id))), RouteEntity::class.java)
    }
}