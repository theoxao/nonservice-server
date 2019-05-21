package com.theoxao.wrap

import io.ktor.application.ApplicationCall
import io.ktor.response.respond


/**
 * @author theo
 * @date 2019/5/21
 */
class ApplicationCallWrap(private val call: ApplicationCall) {

    suspend fun respond(any: Any) {
        call.respond(any)
    }
}