package com.theoxao

import com.theoxao.entities.RouteEntity
import com.theoxao.service.DefaultRouteHandler
import org.bson.types.ObjectId
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


/**
 * create by theoxao on 2019/05/18
 */
@SpringBootApplication(scanBasePackages= ["com"])
open class Application


fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

