package com.theoxao.service

import com.theoxao.annotations.ShylyService
import com.theoxao.common.BaseRouteData
import org.springframework.util.Assert
import java.util.concurrent.ConcurrentHashMap


/**
 * @author theo
 * @date 2019/5/23
 */
@ShylyService
class RouteCacheService {

    val cache: ConcurrentHashMap<String, BaseRouteData> = ConcurrentHashMap()


    fun updateScript(id: String, script: String) {
        Assert.isTrue(cache.containsKey(id), "route does not exist")
        cache[id]!!.script = script
    }
}