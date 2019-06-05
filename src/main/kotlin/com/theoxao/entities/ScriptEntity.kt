package com.theoxao.entities

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

import java.util.ArrayList

/**
 * create by theoxao on 2019/5/24
 */

@Document(collection = "script_data")
class ScriptEntity {

    private val id: ObjectId? = null
    private val originScript: String? = null
    private val parsedScript: String? = null
    private val methods: ArrayList<String>? = null
    /**
     * 0 single method script
     * 1 multi method script
     */
    private val type: Int? = null
}
