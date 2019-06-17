package com.theoxao.repository.mongo

import com.theoxao.entities.ScriptEntity
import com.theoxao.repository.ScriptRepository
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository


/**
 * @author theo
 * @date 2019/6/17
 */
@Repository
class ScriptMongoRepository(private val mongoTemplate: MongoTemplate) : ScriptRepository {
    override fun findById(id: String): ScriptEntity? {
        return mongoTemplate.findById(ObjectId(id), ScriptEntity::class.java)
    }

    override fun save(scriptEntity: ScriptEntity) {
        mongoTemplate.save(scriptEntity)
    }

}