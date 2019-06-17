package com.theoxao.repository.mongo

import com.theoxao.entities.RouteEntity
import com.theoxao.repository.RouteRepository
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import org.springframework.util.Assert


/**
 * @author theo
 * @date 2019/5/20
 */
@Repository
open class RouteMongoRepository(private val mongoTemplate: MongoTemplate

) : RouteRepository {


    override fun findById(id: String): RouteEntity? {
        return mongoTemplate.findById(ObjectId(id), RouteEntity::class.java)
    }

    override fun findAll(): MutableList<RouteEntity> {
        return mongoTemplate.findAll(RouteEntity::class.java)
    }

    override fun save(route: RouteEntity) {
        mongoTemplate.save(route)
    }

    override fun update(route: RouteEntity) {

    }

    override fun updateSelective(route: RouteEntity) {
        Assert.notNull(route.id, "route id should null be null")
    }

    override fun remove(id: String) {
        mongoTemplate.remove(Query.query(Criteria.where("id").`is`(ObjectId(id))), RouteEntity::class.java)
    }
}