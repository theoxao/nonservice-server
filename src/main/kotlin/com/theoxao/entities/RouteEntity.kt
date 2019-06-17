package com.theoxao.entities

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document


/**
 * create by theoxao on 2019/5/18
 */
@Document(collection = "route_data")
open class RouteEntity {

    var id = ObjectId().toHexString()

    /**
     * request uri
     */
    var uri: String = ""

    /**
     * request method
     * TODO maybe support multi method for single route
     */
    var requestMethod: String = "GET"

    var scriptId: ObjectId? = null

    /**
     * use service by default
     */
    var method: String = "service"

    /**
     * script context
     */
    var script: String = ""


}