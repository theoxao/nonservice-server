package com.theoxao.entities

import com.theoxao.route.BaseRouteData
import org.bson.types.ObjectId
import org.intellij.lang.annotations.Language
import org.springframework.data.mongodb.core.mapping.Document


/**
 * @author theo
 * @date 2019/5/20
 */
@Document(collection = "route_data")
class RouteEntity : BaseRouteData() {

}