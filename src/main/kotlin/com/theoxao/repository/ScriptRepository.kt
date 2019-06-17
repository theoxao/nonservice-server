package com.theoxao.repository

import com.theoxao.entities.ScriptEntity
import org.springframework.stereotype.Repository


/**
 * @author theo
 * @date 2019/6/17
 */
@Repository
interface ScriptRepository {

    fun save(scriptEntity: ScriptEntity)

    fun findById(id: String): ScriptEntity?
}