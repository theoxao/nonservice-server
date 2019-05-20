package com.theoxao.common

import com.theoxao.service.ServicesHolder
import io.ktor.application.ApplicationCall


/**
 * @author theo
 * @date 2019/5/20
 */
class ParamWrap(
        val servicesHolder: ServicesHolder,
        private val call: ApplicationCall
) {
    val request = call.request
    val response = call.response
    val parameters = call.parameters
}