package com.theoxao.common

import org.bson.types.ObjectId
import org.intellij.lang.annotations.Language


/**
 * create by theoxao on 2019/5/18
 */
open class BaseRouteData {

    var id = ObjectId().toHexString()

    var path: String = ""

    /**
     * request method
     */
    var method: String = "GET"


    @Language("Groovy")
    var script = ""

    constructor()

    constructor(id: String?, script: String) {
        this.id = id
        this.script = script
    }

    //    /**
//     * handle the request
//     */
//    var handle: (PipelineContext<Unit, ApplicationCall>.() -> Unit)? = null

}