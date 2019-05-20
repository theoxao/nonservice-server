package com.theoxao.annotations

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Service


/**
 * @author theo
 * @date 2019/5/20
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
@Service
annotation class EmbeddedService(@get:AliasFor(annotation = Service::class, attribute = "value") val value: String ="")