package com.theoxao

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


/**
 * create by theoxao on 2019/05/18
 */
@SpringBootApplication
open class Application


fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
