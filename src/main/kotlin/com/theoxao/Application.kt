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
open class Application {

    @Bean
    open fun runner(routeHandler: DefaultRouteHandler): CommandLineRunner {
        return CommandLineRunner {

            val data = RouteEntity()
            data.id=ObjectId().toHexString()
            data.path="/foo"
            //language=Groovy
            data.script = "import com.example.service.FooService\nimport com.theoxao.common.CommonResult\nimport com.theoxao.common.ParamWrap\n\nstatic CommonResult service(ParamWrap paramWrap) {\n    def id = paramWrap.call.parameters.get(\"id\")\n    def fooService = (FooService) paramWrap.servicesHolder.getService(\"fooService\")\n    def duplicate = fooService.duplicate(id)\n    duplicate+=\" duplicated\"\n    duplicate+= \" not from dynamic\"\n    new CommonResult(duplicate)\n}\n"
            routeHandler.addRoute(data)

        }
    }
}


fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

