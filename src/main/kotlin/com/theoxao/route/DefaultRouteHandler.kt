package com.theoxao.route

import io.ktor.http.HttpMethod
import io.ktor.routing.*
import io.ktor.server.engine.ApplicationEngine
import io.ktor.util.AttributeKey
import io.ktor.util.Attributes
import io.ktor.util.pipeline.ContextDsl
import org.springframework.stereotype.Component
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.javaField


/**
 * create by theoxao on 2019/5/18
 */
@Component
class DefaultRouteHandler(private val applicationEngine: ApplicationEngine) : RouteHandler {
    private var baseRoute: Routing?

    init {
        val attributes = applicationEngine.application.attributes
        val attribute = attributes.attribute("ApplicationFeatureRegistry")
        val routeKey = attribute!!.allKeys.filter { it.name == "Routing" }[0] as AttributeKey<Attributes>
        val route: Any? = attribute.attribute("Routing")
        baseRoute = route as? Routing
    }


    fun addRoute(routeData: BaseRouteData) {
        applicationEngine.application.routing {
            markedRoute(routeData.path, routeData.method, routeData.id) {
                handle {
                    routeData.handle?.let { it(this) }
                }
            }
        }
    }

    fun removeRoute(id: String) {
        baseRoute?.childList()!!.forEach { parent ->
            val grandChildList = parent.childList()
            grandChildList.removeIf {
                val key = it.attributes.attribute("ID") as? AttributeKey<String>
                return@removeIf if (key != null) it.attributes.getOrNull(key) == id else false
            }
        }

    }


}


/**
 * use reflect to get childList since its private
 */
fun Route.childList(): MutableList<Route> {
    val field = Route::class.declaredMemberProperties.stream().filter { it.name == "childList" }.findAny().get().javaField
    field?.isAccessible = true
    return field?.get(this) as MutableList<Route>
}

internal val identifyKey = AttributeKey<String>("ID")

/**
 * add a id attribute to route
 */
@ContextDsl
fun Route.markedRoute(path: String, method: HttpMethod, value: String, build: Route.() -> Unit): Route {
    val selector = HttpMethodRouteSelector(method)
    val createRouteFromPath = createRouteFromPath(path)
    val child = createRouteFromPath.createChild(selector)
    child.attributes.put(identifyKey, value)
    return child.apply(build)
}

fun Attributes.attribute(key: String): Attributes? {
    val attributeKey = this.allKeys.filter { it.name == key }[0] as AttributeKey<Attributes>
    return this.getOrNull(attributeKey)
}