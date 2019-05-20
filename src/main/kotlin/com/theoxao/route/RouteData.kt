package com.theoxao.route

import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import io.ktor.util.pipeline.PipelineContext
import io.ktor.util.pipeline.PipelineInterceptor
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
    var method: HttpMethod = HttpMethod.Get


    @Language("Groovy")
    var script = ""


//    /**
//     * handle the request
//     */
//    var handle: (PipelineContext<Unit, ApplicationCall>.() -> Unit)? = null

}