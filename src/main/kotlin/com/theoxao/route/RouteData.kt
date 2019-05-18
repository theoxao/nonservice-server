package com.theoxao.route

import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import io.ktor.util.pipeline.PipelineContext
import io.ktor.util.pipeline.PipelineInterceptor


/**
 * create by theoxao on 2019/5/18
 */
class BaseRouteData {

    var id: String = ""

    var path: String = ""

    /**
     * request method
     */
    var method: HttpMethod = HttpMethod.Get

    /**
     * handle the request
     */
    var handle: (PipelineContext<Unit, ApplicationCall>.() -> Unit)? = null

}